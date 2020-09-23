package utils;

import java.util.UUID;

/**
 * Author: Chevro.Lee <br>
 * Description:
 * Date: Create in  17:06 2020-07-08
 **/
public class UuidUtils {

    /**
     * 获得一个UUID
     */
    public static String getUUID(){
        String uuid = UUID.randomUUID().toString();
        return uuid.replaceAll("-", "");
    }
}
