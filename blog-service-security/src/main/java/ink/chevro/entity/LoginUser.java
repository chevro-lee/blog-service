package ink.chevro.entity;

/**
 * Author: Chevro.Lee <br>
 * Description:
 * Date: Create in  17:10 2020-07-09
 **/
public class LoginUser {

    private String username;

    private String password;

    private Integer rememberMe;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(Integer rememberMe) {
        this.rememberMe = rememberMe;
    }
}
