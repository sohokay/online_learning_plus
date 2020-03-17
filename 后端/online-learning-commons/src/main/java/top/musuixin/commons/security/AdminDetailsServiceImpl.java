package top.musuixin.commons.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 用户登录认证信息查询
 *
 * @author Louis
 * @date Jan 14, 2019
 */
@Service("adminDetailsService")
public class AdminDetailsServiceImpl implements UserDetailsService {
//    @Autowired
//    IAdminService adminService;

    @Override
    public UserDetails loadUserByUsername(String adminName) throws UsernameNotFoundException {
//        Admin admin = adminService.findByName(adminName);
//        if (admin == null) {
//            throw new UsernameNotFoundException("该用户不存在");
//        }
//        // 用户权限列表，根据用户拥有的权限标识与如 @PreAuthorize("hasAuthority('sys:menu:view')") 标注的接口对比，决定是否可以调用接口
//        HashSet<String> hashSet = new HashSet<>();
//        hashSet.add("admin");
//        List<GrantedAuthority> grantedAuthorities = hashSet.stream().map(GrantedAuthorityImpl::new).collect(Collectors.toList());
//        return new JwtUserDetails(admin.getAdminName(), admin.getAdminPassword(), admin.getAdminSalt(), grantedAuthorities);
        return null;
    }
}
