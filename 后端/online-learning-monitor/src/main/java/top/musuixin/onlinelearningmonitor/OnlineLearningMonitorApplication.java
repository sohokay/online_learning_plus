package top.musuixin.onlinelearningmonitor;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAdminServer
public class OnlineLearningMonitorApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlineLearningMonitorApplication.class, args);
    }

}
