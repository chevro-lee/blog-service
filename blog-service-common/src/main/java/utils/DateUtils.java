package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Author: Chevro.Lee <br>
 * Description: 日期工具类
 * Date: Create in  15:13 2019-08-11
 **/
public class DateUtils {

    private static final String SYSTEM_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 获取当前日期时间
     * @return 当前日期时间
     */
    public static String now() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(SYSTEM_DATE_FORMAT);
        return simpleDateFormat.format(new Date());
    }

    /**
     * 日期时间字符串转化为日期格式
     * @param dateStr 日期时间字符串
     * @return 日期
     */
    public static Date stringToDate(String dateStr) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(SYSTEM_DATE_FORMAT);
        try {
            return simpleDateFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 日期时间转化字符串
     * @param date date
     * @return 字符串
     */
    public static String dateToString(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(SYSTEM_DATE_FORMAT);
        return simpleDateFormat.format(date);
    }

    /**
     * 日期转化为时间戳
     * @param date date
     * @return 时间错
     */
    public static String dateToStamp(Date date) {
        long time = date.getTime();
        return String.valueOf(time);
    }

    /**
     * 时间戳转为日期时间格式
     * @param timeStamp 时间戳
     * @return 日期时间格式
     */
    public static String stampToDate(String timeStamp){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(SYSTEM_DATE_FORMAT);
        long time = new Long(timeStamp);
        Date date = new Date(time);
        return simpleDateFormat.format(date);
    }
}
