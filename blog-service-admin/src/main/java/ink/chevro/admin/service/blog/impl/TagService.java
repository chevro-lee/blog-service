package ink.chevro.admin.service.blog.impl;

import base.BaseService;
import ink.chevro.admin.entity.blog.Tag;
import ink.chevro.admin.mybatis.mapper.blog.TagMapper;
import ink.chevro.admin.service.blog.ITagService;
import org.springframework.stereotype.Service;
import ink.chevro.redis.ClientProvider;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: Chevro.Lee <br>
 * Description:
 * Date: Create in  19:30 2019-08-08
 **/
@Service
public class TagService extends BaseService implements ITagService {

    @Resource
    private TagMapper tagMapper;

    @Override
    public List<Tag> listAll() {
        logger.trace("*****************trace**********");
        logger.debug("*************debug*************");
        logger.info("**************info****************");
        logger.warn("****************warn***************");
        logger.error("****************error*************");
        Map<String,String> map = new HashMap<>();
        ClientProvider.redisZSetClient().size("12");
        return tagMapper.selectByExample(null);
    }
}
