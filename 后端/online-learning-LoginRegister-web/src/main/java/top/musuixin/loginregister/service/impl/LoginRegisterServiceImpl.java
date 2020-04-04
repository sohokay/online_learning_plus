package top.musuixin.loginregister.service.impl;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.ReUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.musuixin.entity.UserInfo;
import top.musuixin.entity.Users;
import top.musuixin.loginregister.Bean.LoginBean;
import top.musuixin.loginregister.Bean.RegisterBean;
import top.musuixin.loginregister.Bean.SendStatusSet;
import top.musuixin.loginregister.OnlineLearningLoginRegisterWeb;
import top.musuixin.loginregister.service.LoginRegisterService;
import top.musuixin.mapper.UserInfoMapper;
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
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    AuthenticationManager authenticationManager;


    protected static final Logger logger = LoggerFactory.getLogger(OnlineLearningLoginRegisterWeb.class);
    final String verification = "Verification";
    final String ok = "Ok";
    final String regexMobile = "^1(3|4|5|7|8)\\d{9}$";
    final String identity = "identity";


    @Override
    public HttpResult login(LoginBean loginBean, HttpServletRequest request) {
        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
        Users user = usersMapper.selectOne(queryWrapper.eq("mobile", loginBean.getMobile()));
        if (user == null) {
            return HttpResult.HTTP_BAD_REQUEST("用户不存在");
        }
//        if (!user.getLoginAddress().equals(AddressByIp.getAddress(request.getRemoteAddr()))) {
//            return HttpResult.HTTP_CONFLICT("登录不安全");
//        }
        // 上线后开启
        if (passwordEncoder.matches(loginBean.getPassword(),user.getPassword())) {
            JwtAuthenticatioToken login = SecurityUtils.login(request, String.valueOf(user.getUserId()), user.getPassword(), authenticationManager);
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("token", login.getToken());
            return HttpResult.HTTP_OK(hashMap);
        }

        return null;
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
        System.err.println(registerBean);
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
        if (usersMapper.isUsernameRepeat(registerBean.getUserName()) != 0) {
            return HttpResult.HTTP_BAD_REQUEST("用户名重复");
        }
        registerBean.setPassword(passwordEncoder.encode(registerBean.getPassword()));
        registerBean.setUserRole(request.getHeader(identity));
        usersMapper.insert(registerBean);
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(registerBean.getUserId());
        userInfo.setCreatTime(LocalDateTime.now());
        userInfo.setBirthday(LocalDate.of(2000, 1, 1));
        userInfo.setSex("未知");
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
        if (redisService.get(verification + mobile) != null) {
            // 检测是否重复获取手机验证码
            return HttpResult.HTTP_BAD_REQUEST("十分钟内重复获取");
        }
        int random = RandomUtil.randomInt(100000, 999999);
        //获取随机数，作为验证码
        String sendSms = SendSmsUtil.sendSms(mobile, random);
        //发送验证码
        String code = JSONUtil.parseObj(sendSms).getJSONArray("SendStatusSet").get(0, SendStatusSet.class).getCode();
        //获取手机发送成功的标识
        if (ok.equals(code)) {
            redisService.set(verification + mobile, String.valueOf(random), 60 * 10);
            //存入redis
            logger.info(sendSms);
            return HttpResult.HTTP_OK("发送成功");
        }
        logger.debug(sendSms);
        return HttpResult.HTTP_INTERNAL_ERROR("系统内部错误");
    }
}
