package ink.chevro.admin.enums.system;

/**
 * Author: Chevro.Lee <br>
 * Description: 菜单显示开关枚举
 * Date: Create in  18:10 2020-09-08
 **/
public enum MenuSwitchEnum {

    OFF(0,false),
    ON(1,true)
    ;

    private Integer key;
    private Boolean value;

    MenuSwitchEnum(Integer key, Boolean value) {
        this.key = key;
        this.value = value;
    }

    public Integer getKey() {
        return key;
    }

    public Boolean getValue() {
        return value;
    }

    public static Boolean getValue(String key) {
        for (MenuSwitchEnum menuSwitchEnum : MenuSwitchEnum.values()) {
            if (!key.equals(menuSwitchEnum.key.toString())) {
                continue;
            }
            return menuSwitchEnum.value;
        }
        return false;
    }
}
