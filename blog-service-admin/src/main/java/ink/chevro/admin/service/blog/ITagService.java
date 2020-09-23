package ink.chevro.admin.service.blog;


import ink.chevro.admin.entity.blog.Tag;

import java.util.List;

/**
 * Author: Chevro.Lee <br>
 * Description:
 * Date: Create in  19:29 2019-08-08
 **/
public interface ITagService {

    /**
     * 获取Tag集合
     * @return Tag集合
     */
    List<Tag> listAll();
}
