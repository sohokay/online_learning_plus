package top.musuixin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import top.musuixin.entity.Users;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author musuixin
 * @since 2020-03-31
 */
public interface UsersMapper extends BaseMapper<Users> {
    @Select("select count(*) from users where user_name=#{username} ")
    int isUsernameRepeat(String username);

    @Select("select count(*) from users where mobile=#{mobile} ")
    int isMobileRepeat(String mobile);
}
