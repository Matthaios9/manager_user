package base.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Slf4j
@Configuration
@EnableJpaRepositories(basePackages = {"base.repository"})
@EntityScan("base.entity")
@EnableTransactionManagement
@RequiredArgsConstructor
public class JpaConfig {
}
