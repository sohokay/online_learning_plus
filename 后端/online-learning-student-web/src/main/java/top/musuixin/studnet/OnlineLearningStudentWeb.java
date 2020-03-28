package top.musuixin.studnet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author musuixin
 * @date 2020-03-22 21:22
 */
@SpringBootApplication
@EnableDiscoveryClient
//扫描外部包
@ComponentScan(value = "top.musuixin.*")
public class OnlineLearningStudentWeb {
    public static void main(String[] args) {
        SpringApplication.run(OnlineLearningStudentWeb.class, args);
    }
}
