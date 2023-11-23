package admin.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration(value = "ShareConfig1")
@Getter
public class ShareConfig {
    @Value("${spring.mail.from.user}")
    private String fromEmail;

    @Value("${spring.mail.expire.verify.seconds}")
    private Integer expireVerifyMailSeconds;

    @Value("${spring.mail.verify.max.number.wrong}")
    private Integer maxNumberWrongVerify;

    @Value("${admin.dashboard.page.size}")
    private Integer adminPageSize;

    @Value("${spring.domain.name}")
    private String domainName;

    @Value("${aes.secret.key}")
    private String aesKey;

    @Value("${aes.iv.key}")
    private String aesIvKey;

    @Value("${field.sensitive.list}")
    private String fieldSensitiveList;
}
