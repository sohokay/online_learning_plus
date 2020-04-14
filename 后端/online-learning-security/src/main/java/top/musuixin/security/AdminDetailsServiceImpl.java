package top.musuixin.security;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

	@Qualifier("usersServiceImpl")
	@Autowired
	IUsersService iUsersService;

	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		System.err.println(userId+"load");
		QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
		List<Users> users = iUsersService.list(queryWrapper.eq("user_id", userId));
		if (users.isEmpty()) {
			throw new UsernameNotFoundException("该用户不存在");
		}
		Users user = users.get(0);
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getUserRole()));
		return new JwtUserDetails(String.valueOf(user.getUserId()) , user.getPassword(), authorities);
		//注入JWT中的信息
	}

}
