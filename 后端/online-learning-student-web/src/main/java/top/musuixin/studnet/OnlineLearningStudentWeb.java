package top.musuixin.studnet;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author musuixin
 * @date 2020-03-22 21:22
 */
@SpringBootApplication
@MapperScan(basePackages = {"top.musuixin.mapper","top.musuixin.studnet.mapper"})
@ComponentScan(value = "top.musuixin.*")
public class OnlineLearningStudentWeb {
    public static void main(String[] args) {
        SpringApplication.run(OnlineLearningStudentWeb.class, args);
    }
}
