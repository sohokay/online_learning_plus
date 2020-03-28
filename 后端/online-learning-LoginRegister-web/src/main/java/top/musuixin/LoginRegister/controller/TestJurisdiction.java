package top.musuixin.LoginRegister.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.musuixin.util.HttpResult;

/**
 * @author musuixin
 * @date 2020-03-26 22:31
 */
@RestController
public class TestJurisdiction {
    @GetMapping("/student")
    public HttpResult stu() {
        return HttpResult.ok("stu");
    }
}
