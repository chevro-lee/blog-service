package ink.chevro.admin.mybatis.mapper.user;

import ink.chevro.admin.entity.user.UserNative;
import ink.chevro.admin.entity.user.UserNativeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserNativeMapper {
    long countByExample(UserNativeExample example);

    int deleteByExample(UserNativeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserNative record);

    int insertSelective(UserNative record);

    List<UserNative> selectByExample(UserNativeExample example);

    UserNative selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserNative record, @Param("example") UserNativeExample example);

    int updateByExample(@Param("record") UserNative record, @Param("example") UserNativeExample example);

    int updateByPrimaryKeySelective(UserNative record);

    int updateByPrimaryKey(UserNative record);
}