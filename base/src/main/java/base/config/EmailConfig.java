package base.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class EmailConfig {
	@Value("${mail.host:smtp.gmail.com}")
	private String emailHost;
	@Value("${mail.port:587}")
	private Integer emailPort;
	@Value("${mail.username:nxtung95@gmail.com}")
	private String emailUserName;
	@Value("${mail.password:aqtrsngyspuceysx}")
	private String emailPassword;
	@Value("${mail.auth:true}")
	private String mailAuth;
	@Value("${mail.protocol:smtp}")
	private String mailProtocal;
	@Value("${mail.starttls:smtp}")
	private String mailStartTls;

	@Bean
	public JavaMailSender getJavaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost(emailHost);
		mailSender.setPort(emailPort);

		mailSender.setUsername(emailUserName);
		mailSender.setPassword(emailPassword);

		Properties props = mailSender.getJavaMailProperties();
		props.put("mail.transport.protocol", mailProtocal);
		props.put("mail.smtp.auth", mailAuth);
		props.put("mail.smtp.starttls.enable", mailStartTls);
		props.put("mail.debug", "false");

		return mailSender;
	}
}
