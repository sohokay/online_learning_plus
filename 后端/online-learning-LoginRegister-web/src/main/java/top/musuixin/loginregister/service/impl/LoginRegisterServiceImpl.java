package top.musuixin.loginregister.service.impl;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.ReUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.musuixin.entity.UserInfo;
import top.musuixin.entity.UserThirdAuth;
import top.musuixin.entity.Users;
import top.musuixin.loginregister.Bean.GitHubBean;
import top.musuixin.loginregister.Bean.GitHubLoginBean;
import top.musuixin.loginregister.Bean.LoginBean;
import top.musuixin.loginregister.Bean.RegisterBean;
import top.musuixin.loginregister.OnlineLearningLoginRegisterWeb;
import top.musuixin.loginregister.dto.ChangePhoneDto;
import top.musuixin.loginregister.service.LoginRegisterService;
import top.musuixin.mapper.UserInfoMapper;
import top.musuixin.mapper.UserThirdAuthMapper;
import top.musuixin.mapper.UsersMapper;
import top.musuixin.redis.service.RedisService;
import top.musuixin.security.JwtAuthenticatioToken;
import top.musuixin.security.util.SecurityUtils;
import top.musuixin.util.HttpResult;
import top.musuixin.util.SendSmsUtil;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;

/**
 * @author musuixin
 * @date 2020-03-31 21:23
 */
@Service

public class LoginRegisterServiceImpl implements LoginRegisterService {
    @Autowired
    private RedisService redisService;
    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private  PasswordEncoder passwordEncoder;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private UserThirdAuthMapper userThirdAuthMapper;
    @Autowired
    AuthenticationManager authenticationManager;
    protected static final Logger logger = LoggerFactory.getLogger(OnlineLearningLoginRegisterWeb.class);
    final String verification = "Verification";
    final String loginCode = "loginVerification";
    final String ok = "Ok";
    final String regexMobile = "^1(3|4|5|7|8)\\d{9}$";
    final String identity = "identity";


    @Override
    @Transactional
    public HttpResult gitHubLogin(GitHubLoginBean gitHubLoginBean, HttpServletRequest request) {
        System.err.println(gitHubLoginBean);
        if (gitHubLoginBean.getMobile() != null && gitHubLoginBean.getPassword() != null) {
            //说明用户是要进行绑定第三方账户操作
            QueryWrapper<Users> queryUser = new QueryWrapper<>();
            Users users = usersMapper.selectOne(queryUser.eq("mobile", gitHubLoginBean.getMobile()));
            if (users == null) {
                return HttpResult.HTTP_BAD_REQUEST("用户名或密码错误");
            }
            if (passwordEncoder.matches(gitHubLoginBean.getPassword(), users.getPassword())) {
                //用户信息认证通过 可以绑定第三方账户并登录
                UserThirdAuth userThirdAuth = new UserThirdAuth();
                userThirdAuth.setThirdId(gitHubLoginBean.getGithubId());
                userThirdAuth.setThirdType("GitHub");
                userThirdAuth.setUserId(users.getUserId());
                userThirdAuthMapper.insert(userThirdAuth);
                JwtAuthenticatioToken login = SecurityUtils.login(request, String.valueOf(users.getUserId()), users.getPassword(), authenticationManager);
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("token", login.getToken());
                return HttpResult.HTTP_OK(hashMap);
            }
        }
        String tokenBody;
        try {
            String codeBody = HttpRequest.post("https://github.com/login/oauth/access_token")
                    .form("client_id", "e5885dde9fe33c6e3488")
                    .form("client_secret", "df903a4e2b64fffa72b5e81727ff6e9f65f931a8")
                    .form("code", gitHubLoginBean.getCode()).header("Accept", "application/json").execute().body();
            String accessToken = JSONUtil.parseObj(codeBody).get("access_token", String.class);
            System.err.println(accessToken);
            tokenBody = HttpRequest.get("https://api.github.com/user")
                    .header("Authorization", "token " + accessToken)
                    .header("User-Agent", "随心在线学习系统")
                    .setReadTimeout(1000 * 10).setConnectionTimeout(1000 * 5).execute().body();
        } catch (Exception e) {
            logger.error(e.toString());
            return HttpResult.HTTP_FORBIDDEN("由于大陆网络原因，GiHub超时,请重试或换其他登陆方式");
        }
        GitHubBean gitHubBean = JSONUtil.parseObj(tokenBody).toBean(GitHubBean.class);
        //获取第三方的信息
        if (gitHubBean.getMessage() != null) {
            //如果是code错误
            return HttpResult.HTTP_FORBIDDEN("GitHub第三方认证错误");
        }
        QueryWrapper<UserThirdAuth> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("third_id", gitHubBean.getId());
        UserThirdAuth userThirdAuth = userThirdAuthMapper.selectOne(queryWrapper);
        if (userThirdAuth == null) {
            //在库中没有此第三方认证信息
            HashMap<String, Object> stringObjectHashMap = new HashMap<>();
            stringObjectHashMap.put("content", "请先绑定此第三方账户");
            stringObjectHashMap.put("GitHubId", gitHubBean.getId());
            return HttpResult.HTTP_CONFLICT(stringObjectHashMap);
        }
        Users user = usersMapper.selectById(userThirdAuth.getUserId());
        JwtAuthenticatioToken login = SecurityUtils.login(request, String.valueOf(user.getUserId()), user.getPassword(), authenticationManager);
        return HttpResult.HTTP_OK(login.getToken());
    }

    @Override
    public HttpResult login(LoginBean loginBean, HttpServletRequest request) {
        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
        Users user = usersMapper.selectOne(queryWrapper.eq("mobile", loginBean.getMobile()).eq("user_role", request.getHeader("identity")));
        if (user == null) {
            return HttpResult.HTTP_FORBIDDEN("手机号或密码错误");
        }
//        if (!user.getLoginAddress().equals(AddressByIp.getAddress(request.getRemoteAddr()))) {
////            return HttpResult.HTTP_CONFLICT("登录不安全");
////        }
        // 上线后开启
        if (passwordEncoder.matches(loginBean.getPassword(), user.getPassword())) {
            JwtAuthenticatioToken login = SecurityUtils.login(request, String.valueOf(user.getUserId()), user.getPassword(), authenticationManager);
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("token", login.getToken());
            UserInfo userInfo = userInfoMapper.selectById(user);
            hashMap.put("user", userInfo.getPortrait());
            return HttpResult.HTTP_OK(hashMap);
        }
        return HttpResult.HTTP_FORBIDDEN("手机号或密码错误");
    }

    /**
     * Transactional  开启事务
     * 用户登录服务
     *
     * @param registerBean 前端传来的用户注册参数
     * @param request      用户请求信息
     * @return Http响应
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public HttpResult register(RegisterBean registerBean, HttpServletRequest request) {
        if (redisService.get(verification + registerBean.getMobile()) == null) {
            return HttpResult.HTTP_BAD_REQUEST("没有获取验证码");
        }
        if (!redisService.get(verification + registerBean.getMobile()).equals(registerBean.getVerification())) {
            return HttpResult.HTTP_BAD_REQUEST("验证码错误");
        }
        if (!registerBean.getRePassword().equals(registerBean.getPassword())) {
            return HttpResult.HTTP_BAD_REQUEST("重复密码错误");
        }
        if (request.getHeader(identity) == null) {
            return HttpResult.HTTP_BAD_REQUEST("HEADER ERROR");
        }
        String role = request.getHeader(identity);
        if (!"teacher".equals(role) && usersMapper.isUsernameRepeat(registerBean.getUserName()) != 0) {
            return HttpResult.HTTP_BAD_REQUEST("用户名重复");
        }
        registerBean.setPassword(passwordEncoder.encode(registerBean.getPassword()));
        registerBean.setUserRole(request.getHeader(identity));
        usersMapper.insert(registerBean);
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(registerBean.getUserId());
        userInfo.setCreatTime(LocalDateTime.now());
        userInfo.setBirthday(LocalDate.of(2000, 1, 1));
        userInfo.setSex("保密");
        userInfo.setPortrait("http://img.musuixin.top/%E9%BB%98%E8%AE%A4%E5%A4%B4%E5%83%8F.jpg");
        userInfoMapper.insert(userInfo);
        return HttpResult.HTTP_OK("注册成功");
    }

    /**
     * @param mobile 手机号
     * @return 返回http响应
     */
    @Override
    public HttpResult getCode(String mobile) {
        if (!ReUtil.isMatch(regexMobile, mobile)) {
            // 如匹配手机号错误
            return HttpResult.HTTP_BAD_REQUEST("请输入正确的手机号");
        }
        if (usersMapper.isMobileRepeat(mobile) != 0) {
            //手机号是否重复？？
            return HttpResult.HTTP_BAD_REQUEST("手机号已被注册");
        }
        if (redisService.get(verification + mobile) != null && redisService.getExpire(verification + mobile) > 540L) {
            // 检测是否重复获取手机验证码
            return HttpResult.HTTP_BAD_REQUEST("一分钟内重复获取");
        }
        int random = RandomUtil.randomInt(100000, 999999);
        //获取随机数，作为验证码
        String sendSms = SendSmsUtil.sendSms(mobile, random, 564902);
        //发送验证码
        //获取手机发送成功的标识
        if (ok.equals(sendSms)) {
            redisService.set(verification + mobile, String.valueOf(random), 60 * 10);
            //存入redis
            logger.info(sendSms);
            return HttpResult.HTTP_OK("发送成功");
        }
        logger.debug(sendSms);
        return HttpResult.HTTP_INTERNAL_ERROR("系统内部错误");
    }

    @Override
    public HttpResult getLoginCode(String mobile) {
        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
        if (!ReUtil.isMatch(regexMobile, mobile)) {
            // 如匹配手机号错误
            return HttpResult.HTTP_BAD_REQUEST("请输入正确的手机号");
        }
        if (redisService.get(loginCode + mobile) != null && redisService.getExpire(loginCode + mobile) > 540L) {
            // 检测是否重复获取手机验证码
            return HttpResult.HTTP_BAD_REQUEST("一分钟内重复获取");
        }
        if (usersMapper.selectOne(queryWrapper.eq("mobile", mobile)) != null) {
            int randomInt = RandomUtil.randomInt(100000, 999999);
            redisService.set(loginCode + mobile, String.valueOf(randomInt), 600);
            SendSmsUtil.sendSms(mobile, randomInt, 579167);
            return HttpResult.HTTP_OK("发送成功");
        }
        return HttpResult.HTTP_BAD_REQUEST("手机号未注册");
    }

    @Override
    public HttpResult phoneLogin(ChangePhoneDto changePhoneDto, HttpServletRequest request) {
        if (changePhoneDto.getCode().equals(redisService.get(loginCode + changePhoneDto.getMobile()))) {
            QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
            Users users = usersMapper.selectOne(queryWrapper.eq("mobile", changePhoneDto.getMobile()));
            if (!users.getUserRole().equals(request.getHeader("identity"))) {
                return HttpResult.HTTP_BAD_REQUEST("身份错误");
            }
            JwtAuthenticatioToken token = SecurityUtils.login(request, String.valueOf(users.getUserId()), users.getPassword(), authenticationManager);
            String portrait = userInfoMapper.selectById(users.getUserId()).getPortrait();
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("token", token.getToken());
            hashMap.put("user", portrait);
            return HttpResult.HTTP_OK(hashMap);
        }
        return HttpResult.HTTP_BAD_REQUEST("验证码错误");
    }
}
