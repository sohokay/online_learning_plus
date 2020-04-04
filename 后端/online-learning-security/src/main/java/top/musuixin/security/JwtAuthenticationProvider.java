package top.musuixin.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.time.LocalDateTime;

/**
 * 身份验证提供者
 *
 * @author Louis
 * @date Jan 14, 2019
 */
public class JwtAuthenticationProvider extends DaoAuthenticationProvider {

	public JwtAuthenticationProvider(UserDetailsService userDetailsService) {
		setUserDetailsService(userDetailsService);
	}

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		logger.info(userDetails.getUsername() + " 登录" + LocalDateTime.now());
	}

}