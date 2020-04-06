package com.encircle360.oss.m2d.client.mayan;

import com.encircle360.oss.m2d.config.FeignConfig;
import com.encircle360.oss.m2d.dto.mayan.MayanDocumentDTO;
import com.encircle360.oss.m2d.dto.mayan.MayanPageDTO;
import com.encircle360.oss.m2d.dto.mayan.MayanTagDTO;
import com.encircle360.oss.m2d.dto.mayan.NewDocumentTagRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "mayanAPIClient", configuration = FeignConfig.class, url = "${mayan.api.base-url}")
public interface MayanAPIClient {

    @GetMapping("/document_types/{documentTypeId}/documents/")
    MayanPageDTO<MayanDocumentDTO> getDocumentsByDocumentTypeId(@PathVariable("documentTypeId") Long documentTypeId, @RequestParam("page") Integer page);

    @GetMapping("/documents/{documentId}/tags/")
    MayanPageDTO<MayanTagDTO> getTagsByDocumentId(@PathVariable("documentId") Long documentId);

    @GetMapping("/documents/{documentId}/download/")
    byte[] downloadDocumentById(@PathVariable("documentId") Long documentId);

    @PostMapping("/documents/{documentId}/tags/")
    void attachTagToDocument(@PathVariable("documentId") Long documentId, @RequestBody NewDocumentTagRequestDTO newDocumentTagRequest);
}
