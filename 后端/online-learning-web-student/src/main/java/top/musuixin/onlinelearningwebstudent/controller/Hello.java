package top.musuixin.onlinelearningwebstudent.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.musuixin.commons.Hi;

/**
 * @author musuixin
 * @date 2020-03-03 18:14
 */
@RestController
@CrossOrigin
@RequestMapping("/hi")
public class Hello {
    @GetMapping("/hi")
    public String Hi() {
        Hi hi = new Hi();
        return hi.say();
    }
}
