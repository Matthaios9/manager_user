package admin.controller;

import base.enums.RoleEnum;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

public class BaseController {
    boolean isRoleAdmin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return false;
        }
        GrantedAuthority authority = authentication.getAuthorities().stream().findFirst().get();
        return RoleEnum.ADMIN.label().equalsIgnoreCase(authority.getAuthority());
    }
}
