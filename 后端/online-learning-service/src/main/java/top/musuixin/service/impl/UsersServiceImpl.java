package top.musuixin.service.impl;

import top.musuixin.entity.Users;
import top.musuixin.mapper.UsersMapper;
import top.musuixin.service.IUsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author musuixin
 * @since 2020-03-26
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements IUsersService {

}
