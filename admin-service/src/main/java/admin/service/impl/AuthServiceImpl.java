package admin.service.impl;

import admin.config.ShareConfig;
import admin.service.AuthService;
import base.entity.User;
import base.service.HistoryAccessService;
import base.utils.SecurityUtils;
import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private ShareConfig shareConfig;
    @Autowired
    private HistoryAccessService historyAccessService;

    @Override
    public void authAdmin(User user, String endpoint) {
        try {
            String userIdEncrypt = SecurityUtils.AESEncrypt(shareConfig.getAesKey(), String.valueOf(user.getId()), shareConfig.getAesIvKey());
            Authentication authentication = new UsernamePasswordAuthenticationToken(userIdEncrypt, user.getEmail() + "-" + user.getPhone(),
                    AuthorityUtils.createAuthorityList(user.getRole().getRole()));

            SecurityContext securityContext = SecurityContextHolder.getContext();
            securityContext.setAuthentication(authentication);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            historyAccessService.save(ThreadContext.get("traceId"), "EXCEPTION", e.getMessage(), endpoint);
        }
    }

    @Override
    public boolean checkAuth(User user, String endpoint) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null && authentication.isAuthenticated()) {
                if (authentication instanceof UsernamePasswordAuthenticationToken) {
                    String authEmailPhone = (String) authentication.getCredentials();
                    String authEmail = authEmailPhone.split("-")[0];
                    String authPhone = authEmailPhone.split("-")[1];
                    if (!user.getEmail().equals(authEmail) || !user.getPhone().equals(authPhone)) {
                        return false;
                    }
                    return true;
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            historyAccessService.save(ThreadContext.get("traceId"), "EXCEPTION", e.getMessage(), endpoint);
        }
        return false;
    }

    @Override
    public boolean checkAuth(String endpoint) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null && authentication.isAuthenticated()) {
                if (authentication instanceof UsernamePasswordAuthenticationToken) {
                    String authEmailPhone = (String) authentication.getCredentials();
                    if (!Strings.isNullOrEmpty(authEmailPhone)) {
                        return true;
                    }
                    return false;
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            historyAccessService.save(ThreadContext.get("traceId"), "EXCEPTION", e.getMessage(), endpoint);
        }
        return false;
    }
}
