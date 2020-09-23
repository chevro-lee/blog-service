package ink.chevro.admin.service.blogger.impl;

import base.BaseService;
import ink.chevro.admin.entity.blogger.Blogger;
import ink.chevro.admin.entity.blogger.BloggerRole;
import ink.chevro.admin.enums.blogger.RoleEnums;
import ink.chevro.admin.mybatis.mapper.blogger.BloggerMapper;
import ink.chevro.admin.mybatis.mapper.blogger.BloggerRoleMapper;
import ink.chevro.admin.service.blogger.IBloggerService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.UuidUtils;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Author: Chevro.Lee <br>
 * Description:
 * Date: Create in  10:33 2020-07-09
 **/
@Service
public class BloggerService extends BaseService implements IBloggerService {

    @Resource
    private BloggerMapper bloggerMapper;

    @Resource
    private BloggerRoleMapper bloggerRoleMapper;

    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Blogger findByName(String name) {
        return bloggerMapper.selectByName(name);
    }

    @Override
    public Blogger getBloggerInfo(String token, String name) {
        return findByName(name);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Blogger insertBlogger(Blogger blogger) {
        // 新增用户
        String bloggerId = UuidUtils.getUUID();
        blogger.setId(bloggerId);
        blogger.setPassword(bCryptPasswordEncoder.encode(blogger.getPassword()));
        blogger.setCreateTime(new Date());
        bloggerMapper.insertSelective(blogger);
        BloggerRole bloggerRole = new BloggerRole();
        bloggerRole.setBloggerId(bloggerId);
        // 身份为博主
        bloggerRole.setRoleId(RoleEnums.ROLE_ADMIN.getId());
        bloggerRole.setCreateTime(new Date());
        bloggerRoleMapper.insertSelective(bloggerRole);
        return blogger;
    }

    @Override
    public Blogger register(Blogger blogger) {
        Blogger newBlogger = insertBlogger(blogger);
        // todo 发送邮件
        return newBlogger;
    }
}
