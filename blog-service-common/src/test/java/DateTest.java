import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Author: Chevro.Lee <br>
 * Description:
 * Date: Create in  15:22 2019-08-11
 **/
public class DateTest {

    public static void main(String[] args) {
        new DateTest().test5();
        new DateTest().test6();
    }

    /**
     * 把当前时间格式为指定格式
     */
    public void test5(){
        //获得当前时间
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");
        String format = ldt.format(dtf);
        System.out.println(format);
    }

    /**
     * 把指定字符串格式化为日期
     */
    public void test6(){
        String str1="2018-07-05 12:24:12";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime parse = LocalDateTime.parse(str1, dtf);
        System.out.println(parse);
    }
}
