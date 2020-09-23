package ink.chevro.admin.service.blogger;


import ink.chevro.admin.entity.blogger.Blogger;

/**
 * Author: Chevro.Lee <br>
 * Description:
 * Date: Create in  10:32 2020-07-09
 **/
public interface IBloggerService {

    /**
     * 通过名字查询
     * @param name
     * @return
     */
    Blogger findByName(String name);

    /**
     * 获取博主信息
     * @param name
     * @param token
     * @return
     */
    Blogger getBloggerInfo(String token, String name);

    /**
     * 新增用户
     * @param blogger
     * @return
     */
    Blogger insertBlogger(Blogger blogger);

    /**
     * 注册
     * @param blogger
     * @return
     */
    Blogger register(Blogger blogger);
}
