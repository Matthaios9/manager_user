package base.service.impl;

import base.service.EmailService;
import base.service.HistoryAccessService;
import brave.Tracer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class EmailServiceImpl implements EmailService {
    @Autowired
    private JavaMailSender emailSender;
    @Autowired
    private Tracer tracer;
    @Autowired
    private HistoryAccessService historyAccessService;

    @Override
    public void sendEmail(String from, String to, String subject, String text) {
        String logEmail = "Send mail from " + from + ", to: " + to + ", subject: " + subject + ", message:" + text;
        CompletableFuture.runAsync(() -> {
            try {
                SimpleMailMessage message = new SimpleMailMessage();
                message.setFrom(from);
                message.setTo(to);
                message.setSubject(subject);
                message.setText(text);
                log.info(logEmail);
                emailSender.send(message);
                log.info("Send mail successfully!");
            } catch (Exception e) {
                log.info(e.getMessage(), e);
            }
        });
        historyAccessService.save(String.valueOf(tracer.currentSpan().context().traceId()), "[SendEmail]", logEmail, "");
    }
}
