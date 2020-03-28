package top.musuixin.studnet.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.musuixin.redis.service.impl.RedisServiceImpl;
import top.musuixin.studnet.OnlineLearningStudentWeb;

/**
 * @author musuixin
 * @date 2020-03-22 21:36
 */
@RestController
public class hello {
    @Autowired
    RedisServiceImpl redisService;
    protected static final Logger logger = LoggerFactory.getLogger(OnlineLearningStudentWeb.class);

    @GetMapping("/hello")
    public Object hello() {
        logger.info("hello log");
        System.err.println("hello");
        return redisService.get("hello");
//         "hello";
    }
}
