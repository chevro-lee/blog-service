package ink.chevro.admin.mybatis.mapper.blogger;

import ink.chevro.admin.entity.blogger.BloggerRole;
import ink.chevro.admin.entity.blogger.BloggerRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BloggerRoleMapper {
    long countByExample(BloggerRoleExample example);

    int deleteByExample(BloggerRoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BloggerRole record);

    int insertSelective(BloggerRole record);

    List<BloggerRole> selectByExample(BloggerRoleExample example);

    BloggerRole selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BloggerRole record, @Param("example") BloggerRoleExample example);

    int updateByExample(@Param("record") BloggerRole record, @Param("example") BloggerRoleExample example);

    int updateByPrimaryKeySelective(BloggerRole record);

    int updateByPrimaryKey(BloggerRole record);
}