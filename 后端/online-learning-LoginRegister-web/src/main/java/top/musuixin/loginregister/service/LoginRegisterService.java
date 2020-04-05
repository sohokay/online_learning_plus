package top.musuixin.loginregister.service;

import top.musuixin.loginregister.Bean.GitHubLoginBean;
import top.musuixin.loginregister.Bean.LoginBean;
import top.musuixin.loginregister.Bean.RegisterBean;
import top.musuixin.util.HttpResult;

import javax.servlet.http.HttpServletRequest;

public interface LoginRegisterService {
    HttpResult gitHubLogin(GitHubLoginBean gitHubLoginBean,HttpServletRequest request);

    HttpResult login(LoginBean loginBean,HttpServletRequest request);

    HttpResult register(RegisterBean registerBean, HttpServletRequest request);

    HttpResult getCode(String mobile);

}
