package com.encircle360.oss.m2d.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;

/**
 * A simple email service to send emails with attachments.
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EmailService {

    private final JavaMailSender mailSender;

    public boolean sendMessageWithAttachment(String to, String subject, String text, String attachmentFilename,
                                          byte[] attachmentData, String attachmentContentType) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text);

            ByteArrayDataSource attachment = new ByteArrayDataSource(attachmentData, attachmentContentType);
            helper.addAttachment(attachmentFilename, attachment);

            mailSender.send(message);
            return true;
        } catch (MessagingException me) {
            log.error("MessagingException occured. Couldn't send mail with attachment.", me);
            return false;
        }
    }
}
