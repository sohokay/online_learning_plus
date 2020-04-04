package top.musuixin.loginregister.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.musuixin.loginregister.Bean.LoginBean;
import top.musuixin.loginregister.Bean.RegisterBean;
import top.musuixin.loginregister.service.LoginRegisterService;
import top.musuixin.util.HttpResult;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author musuixin
 * @date 2020-03-26 20:27
 */
@RestController
@RequestMapping("/v1")
public class LoginRegister {
    @Autowired
    private LoginRegisterService loginRegisterService;


    @GetMapping("/githublogin")
    public HttpResult test(String code) {
//        System.err.println(JwtTokenUtils.getToken(request));

        System.err.println(code);
        return null;

    }

    @PostMapping("/login")
    public HttpResult loginUser(@RequestBody LoginBean loginBean, HttpServletRequest request) throws IOException {
        return loginRegisterService.login(loginBean, request);
    }

    @PostMapping("/register")
    public HttpResult registerUser(@RequestBody RegisterBean registerBean, HttpServletRequest request) {
        return loginRegisterService.register(registerBean, request);
    }

    @GetMapping("/verification")
    public HttpResult getCode(String mobile) {
        return loginRegisterService.getCode(mobile);
    }
}
