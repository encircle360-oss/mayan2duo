package com.encircle360.oss.m2d.service.mayan;

import com.encircle360.oss.m2d.client.mayan.MayanAPIClient;
import com.encircle360.oss.m2d.dto.mayan.MayanDocumentDTO;
import com.encircle360.oss.m2d.dto.mayan.MayanPageDTO;
import com.encircle360.oss.m2d.dto.mayan.MayanTagDTO;
import com.encircle360.oss.m2d.dto.mayan.NewDocumentTagRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service that contains useful methods to work with mayan-edms.
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MayanService {

    private final MayanAPIClient mayanAPIClient;

    public MayanPageDTO<MayanDocumentDTO> getDocumentsByDocumentTypeId(Long documentTypeId, Integer page) {
        return mayanAPIClient.getDocumentsByDocumentTypeId(documentTypeId, page);
    }

    public MayanPageDTO<MayanTagDTO> getTagsByDocumentId(Long documentId) {
        return mayanAPIClient.getTagsByDocumentId(documentId);
    }

    public byte[] downloadDocumentById(Long documentId) {
        return mayanAPIClient.downloadDocumentById(documentId);
    }

    public void attachTagToDocument(Long documentId, Long tagId) {
        mayanAPIClient.attachTagToDocument(documentId, NewDocumentTagRequestDTO.builder().tagId(tagId).build());
    }
}
