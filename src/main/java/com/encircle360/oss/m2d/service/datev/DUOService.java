package com.encircle360.oss.m2d.service.datev;

import com.encircle360.oss.m2d.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service that handles communication between DATEV Unternehmen Online and the application.
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DUOService {

    private final EmailService emailService;

    public boolean sendDocumentToDUO(String toEmail, String subject, String body, String filename, byte[] documentData, String documentContentType) {
        return emailService.sendMessageWithAttachment(toEmail, subject, body, filename, documentData, documentContentType);
    }
}
