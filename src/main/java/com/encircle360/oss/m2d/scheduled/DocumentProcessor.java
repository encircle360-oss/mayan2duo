package com.encircle360.oss.m2d.scheduled;


import com.encircle360.oss.m2d.dto.mayan.MayanDocumentDTO;
import com.encircle360.oss.m2d.dto.mayan.MayanPageDTO;
import com.encircle360.oss.m2d.dto.mayan.MayanTagDTO;
import com.encircle360.oss.m2d.service.datev.DUOService;
import com.encircle360.oss.m2d.service.mayan.MayanService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A Document Processor which doesn't run concurrently and runs on a fixed delay.
 * It processes all by application.yml configured document types from mayan-edms and sends them to the correct DATEV Unternehmen Online mail inbox.
 * In this case you only have to assign or import documents with the correct document type within mayan and
 * mayan2duo will send them to the configured inbox for each type.
 */
@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DocumentProcessor {

    private final MayanService mayanService;
    private final DUOService duoService;

    @Value("#{${mayan2duo.mapping}}")
    private HashMap<Long, String> mapping;

    @Value("${mayan2duo.tags.done-id}")
    private Long doneTagId;

    @Scheduled(fixedDelayString = "${mayan2duo.fixed-delay}")
    public void processDocuments() {
        log.info("Processing documents...");

        // iterate over all mappings <mayanDocumentTypeId,duoEmailAddress>
        this.mapping.forEach((mayanDocumentTypeId, duoEmailAddress) -> {

            // get all mayan documents by document types
            MayanPageDTO<MayanDocumentDTO> documentsByDocumentTypeId =
                    mayanService.getDocumentsByDocumentTypeId(mayanDocumentTypeId);

            // filter the documents that don't have the doneTag
            List<MayanDocumentDTO> documentsNotProcessed = documentsByDocumentTypeId.getResults().stream()
                    .filter(mayanDocument -> {
                        MayanPageDTO<MayanTagDTO> mayanTagPage = mayanService.getTagsByDocumentId(mayanDocument.getId());
                        return mayanTagPage.getResults()
                                .stream()
                                .noneMatch(mayanTag -> mayanTag.getId().equals(this.doneTagId));
                    })
                    .collect(Collectors.toList());

            // push documents to DUO
            documentsNotProcessed.forEach(mayanDocument -> {
                byte[] documentData = mayanService.downloadDocumentById(mayanDocument.getId());
                if(duoService.sendDocumentToDUO(duoEmailAddress, "Document " + mayanDocument.getId() +": " + mayanDocument.getLabel(),
                        "This is an email from mayan2duo.", mayanDocument.getLabel(), documentData, "application/pdf")) {
                    log.info("Successfully sent document {} to DATEV Unternehmen Online ({}).", mayanDocument.getId(), duoEmailAddress);

                    // if pushed to DUO, assign done tag
                    mayanService.attachTagToDocument(mayanDocument.getId(), this.doneTagId);
                    log.info("Successfully attached done tag {} to document {} .", this.doneTagId, mayanDocument.getId());
                } else {
                    log.error("Failed to send document {} to DATEV Unternehmen Online ({})", mayanDocument.getId(), duoEmailAddress);
                }
            });
        });

        log.info("Processing of documents done.");
    }
}
