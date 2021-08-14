package org.pettyfox.base.web.context;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

/**
 * @author pettyfox
 * @version 1.0
 */
public class UserContext {
    public static Long getUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (null == authentication || null == authentication.getPrincipal()) {
            return null;
        }
        if (authentication.getPrincipal() instanceof User) {
            User user = (User) authentication.getPrincipal();
            if (StringUtils.isBlank(user.getUsername())) {
                return null;
            }
            return Long.valueOf(user.getUsername());
        } else if (authentication.getPrincipal() instanceof UserSession) {
            UserSession user = (UserSession) authentication.getPrincipal();
            return user.getAccountId();
        }
        return null;

    }
    public static Long getRoleId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (null == authentication || null == authentication.getPrincipal()) {
            return null;
        }
        if (authentication.getPrincipal() instanceof User) {
            return null;
        } else if (authentication.getPrincipal() instanceof UserSession) {
            UserSession user = (UserSession) authentication.getPrincipal();
            return user.getRoleId();
        }
        return null;

    }
}
