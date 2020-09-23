package ink.chevro.admin.bussiness.dao.blog.impl;

import ink.chevro.admin.bussiness.dao.blog.ITagDAO;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Author: Chevro.Lee <br>
 * Description:
 * Date: Create in  14:31 2019-08-09
 **/
@Component
public class TagDAO implements ITagDAO {

    public char value[];


    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        int i = 0;
        while (i++ <= 10) {
            list.add(i);
        }
        System.out.println(list);
    }
}
