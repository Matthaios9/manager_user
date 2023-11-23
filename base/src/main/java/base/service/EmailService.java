package base.service;

import base.entity.VerificationCode;

public interface EmailService {
    void sendEmail(String from, String to, String subject, String text);
}
