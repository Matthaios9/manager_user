package admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(scanBasePackages = {"admin", "base"}, exclude = { SecurityAutoConfiguration.class, UserDetailsServiceAutoConfiguration.class })
public class AdminServiceApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(AdminServiceApplication.class, args);
    }

}
