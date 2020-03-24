package top.musuixin.studnet.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author musuixin
 * @date 2020-03-22 21:36
 */
@RestController
public class hello {
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}
