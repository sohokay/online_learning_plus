package top.musuixin.service.impl;

import top.musuixin.entity.UserThirdAuth;
import top.musuixin.mapper.UserThirdAuthMapper;
import top.musuixin.service.IUserThirdAuthService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 第三方认证表 服务实现类
 * </p>
 *
 * @author musuixin
 * @since 2020-03-26
 */
@Service
public class UserThirdAuthServiceImpl extends ServiceImpl<UserThirdAuthMapper, UserThirdAuth>
		implements IUserThirdAuthService {

}
