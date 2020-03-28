package top.musuixin.security;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import top.musuixin.entity.Users;
import top.musuixin.service.IUsersService;

import java.util.ArrayList;
import java.util.List;


/**
 * 用户登录认证信息查询
 *
 * @author Louis
 * @date Jan 14, 2019
 */
@Service("adminDetailsService")
public class AdminDetailsServiceImpl implements UserDetailsService {
    @Autowired
    IUsersService iUsersService;

    @Override
    public UserDetails loadUserByUsername(String mobile) throws UsernameNotFoundException {
        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
        System.err.println("执行了数据库查询");
        List<Users> users = iUsersService.list(queryWrapper.eq("mobile", mobile));
        if (users.isEmpty()) {
            System.err.println("执行了数据库查询");
            throw new UsernameNotFoundException("该用户不存在");
        }
        Users user = users.get(0);
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getUserRole()));
        return new JwtUserDetails(user.getUserName(), user.getPassword(), user.getSalt(), authorities);
    }
}
