package top.musuixin.LoginRegister.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import top.musuixin.LoginRegister.Bean.LoginBean;
import top.musuixin.redis.service.RedisService;
import top.musuixin.security.JwtAuthenticatioToken;
import top.musuixin.security.util.SecurityUtils;
import top.musuixin.util.HttpResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author musuixin
 * @date 2020-03-26 20:27
 */
@RestController
public class LoginRegister {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisService redisService;

    @GetMapping("/test")
    public String test(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.err.println(request.getRemoteHost());
        return "xixi";
    }

    @PostMapping("/login")
    public HttpResult loginUser(@RequestBody LoginBean loginBean, HttpServletRequest request, HttpServletResponse response) throws IOException {
        JwtAuthenticatioToken login = SecurityUtils.login(request, loginBean.getMobile(), loginBean.getPassword(), authenticationManager);
        return HttpResult.ok(login.getToken());

    }

    @PostMapping("/register")
    public HttpResult registerUser() {
        redisService.set("323", "323", 10);
        return null;
    }
}
