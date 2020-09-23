package ink.chevro.admin.mybatis.mapper.user;

import ink.chevro.admin.entity.user.UserActive;
import ink.chevro.admin.entity.user.UserActiveExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserActiveMapper {
    long countByExample(UserActiveExample example);

    int deleteByExample(UserActiveExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserActive record);

    int insertSelective(UserActive record);

    List<UserActive> selectByExample(UserActiveExample example);

    UserActive selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserActive record, @Param("example") UserActiveExample example);

    int updateByExample(@Param("record") UserActive record, @Param("example") UserActiveExample example);

    int updateByPrimaryKeySelective(UserActive record);

    int updateByPrimaryKey(UserActive record);
}