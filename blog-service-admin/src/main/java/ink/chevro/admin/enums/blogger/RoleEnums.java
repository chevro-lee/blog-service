package ink.chevro.admin.enums.blogger;

/**
 * Author: Chevro.Lee <br>
 * Description:
 * Date: Create in  9:50 2020-07-25
 **/
public enum RoleEnums {

    ROLE_ADMIN(1,"ROLE_ADMIN"),
    ROLE_BLOGGER(2,"ROLE_BLOGGER")
    ;

    private Integer id;

    private String role;

    RoleEnums(Integer id, String role) {
        this.id = id;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public String getRole() {
        return role;
    }
}
