package top.musuixin.loginregister.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import top.musuixin.security.JwtAuthenticationFilter;
import top.musuixin.security.JwtAuthenticationProvider;


/**
 * Spring Security配置
 * EnableWebSecurity 开启Spring Security
 *
 * @author Louis
 * @date Jan 14, 2019
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Qualifier("adminDetailsService")
    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * 此处执行认证流程
     *
     * @param auth
     * @throws Exception
     */
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(new JwtAuthenticationProvider(userDetailsService));
    }

    /**
     * 此处对用户进行授权
     * 所有url都要经过此处
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 禁用 csrf, 由于使用的是JWT，我们这里不需要csrf
        http.cors().and().csrf().disable()
                .authorizeRequests()
                // 跨域预检请求
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers("/v1/login","/v1/register","/v1/githublogin","/v1/verification","/v1/login-verification","/v1/phone-login").permitAll()
                                .antMatchers(
                        "/doc.html",
                        "/api-docs-ext	",
                        "/swagger-resources	",
                        "/api-docs",
                        "/swagger-ui.html",
                        "/swagger-resources/configuration/ui",
                        "/swagger-resources/configuration/security",
                        "/webjars/**",
                        "/service-worker.js",
                        "/swagger-resources",
                                        "/v2/api-docs"
                        ).permitAll()

                .anyRequest().authenticated();
        // 退出登录处理器
        http.logout().logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler());
        http.addFilterBefore(new JwtAuthenticationFilter(authenticationManager()), UsernamePasswordAuthenticationFilter.class);
        //token验证过滤器此处关闭此过滤器
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}