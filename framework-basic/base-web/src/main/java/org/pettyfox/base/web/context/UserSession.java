package org.pettyfox.base.web.context;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @author Petty Fox
 * @version 1.0
 */
public class UserSession implements UserDetails {
    private String username;
    private String password;
    private Long accountId;
    private Long roleId;
    private Collection<? extends GrantedAuthority> grants;

    public UserSession(String username, String password, Long accountId,Long roleId, Collection<? extends GrantedAuthority> grants) {
        this.username = username;
        this.password = password;
        this.accountId = accountId;
        this.grants = grants;
        this.roleId = roleId;
    }
    public Long getAccountId(){
        return accountId;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grants;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Long getRoleId() {
        return roleId;
    }
}
