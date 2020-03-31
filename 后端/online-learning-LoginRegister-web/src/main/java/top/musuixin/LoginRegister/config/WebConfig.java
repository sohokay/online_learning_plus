package top.musuixin.LoginRegister.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author musuixin
 * @date 2020-03-30 11:13
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")//允许跨域访问的路径
                .allowedOrigins("*")//允许跨域访问的源
                .allowedMethods("GET", "POST", "DELETE", "PUT")
                .maxAge(168000)
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}