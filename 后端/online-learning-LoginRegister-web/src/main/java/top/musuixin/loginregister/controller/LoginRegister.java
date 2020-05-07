package top.musuixin.loginregister.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.musuixin.loginregister.Bean.GitHubLoginBean;
import top.musuixin.loginregister.Bean.LoginBean;
import top.musuixin.loginregister.Bean.RegisterBean;
import top.musuixin.loginregister.dto.ChangePhoneDto;
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
@Api(tags = "用户注册登录")
public class LoginRegister {
    @Autowired
    private LoginRegisterService loginRegisterService;


    @PostMapping("/github_login")
    @ApiOperation("GitHub登录")
    public HttpResult test(@RequestBody GitHubLoginBean gitHubLoginBean, HttpServletRequest request) {
        return loginRegisterService.gitHubLogin(gitHubLoginBean, request);
    }

    @PostMapping("/login")
    @ApiOperation("账户密码登录")
    public HttpResult loginUser(@RequestBody LoginBean loginBean, HttpServletRequest request) throws IOException {
        return loginRegisterService.login(loginBean, request);
    }

    @PostMapping("/register")
    @ApiOperation("用户注册")
    public HttpResult registerUser(@RequestBody RegisterBean registerBean, HttpServletRequest request) {
        return loginRegisterService.register(registerBean, request);
    }

    @GetMapping("/verification")
    @ApiOperation("获取注册验证码")
    public HttpResult getCode(String mobile) {
        return loginRegisterService.getCode(mobile);
    }

    @ApiOperation("手机登录验证码")
    @GetMapping("/login-verification")
    public HttpResult getLoginCode(String mobile) {
        return loginRegisterService.getLoginCode(mobile);
    }

    @ApiOperation("手机登录")
    @PostMapping("/phone-login")
    public HttpResult phoneLogin(@RequestBody ChangePhoneDto changePhoneDto, HttpServletRequest request) {
        return loginRegisterService.phoneLogin(changePhoneDto, request);
    }
}
