package top.musuixin.loginregister;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 *
 *
 *
 *
 * @author musuixin
 * @date 2020-03-22 21:22
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages = "top.musuixin.mapper")
@ComponentScan(value = "top.musuixin.*")
public class OnlineLearningLoginRegisterWeb {

    public static void main(String[] args) {
        SpringApplication.run(OnlineLearningLoginRegisterWeb.class, args);
    }

}
