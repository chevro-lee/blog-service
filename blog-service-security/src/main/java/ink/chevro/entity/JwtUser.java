package ink.chevro.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

/**
 * Author: Chevro.Lee <br>
 * Description:
 * Date: Create in  17:29 2020-07-08
 **/
public class JwtUser implements UserDetails {

    private String id;

    private String userName;

    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    /**
     * 账号是否过期
     */
    private boolean isAccountNonExpired = true;

    /**
     * 账号是否被锁定
     */
    private boolean isAccountNonLock = true;

    /**
     * 凭证是否有效
     */
    private boolean isCredentialsNonExpired = true;

    /**
     * 账号是否被锁定
     */
    private boolean isEnabled = true;

    public JwtUser(String bloggerId, String username, String password, String role) {
        this.id = bloggerId;
        this.userName = username;
        this.password = password;
        this.authorities = Collections.singletonList(new SimpleGrantedAuthority(role));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    public String getId() {
        return this.id;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLock;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
