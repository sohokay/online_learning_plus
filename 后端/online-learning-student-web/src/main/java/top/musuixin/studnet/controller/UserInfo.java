package top.musuixin.studnet.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.musuixin.studnet.dto.ChangePasswordDto;
import top.musuixin.studnet.dto.ChangePhoneDto;
import top.musuixin.studnet.dto.UserAllInfoDto;
import top.musuixin.studnet.service.UserService;
import top.musuixin.util.HttpResult;

import javax.validation.Valid;

/**
 * @author musuixin
 * @date 2020-03-22 21:36
 */
@RestController
@RequestMapping("/v1")
@Slf4j
@Api(tags = "用户信息")
public class UserInfo {
    @Autowired
    UserService userService;

    @GetMapping("/user-info")
    @ApiOperation("获取用户信息")
    public HttpResult getUserInfo() {
        return userService.getUserAllInfo();
    }

    @PutMapping("/user-info")
    @ApiOperation("修改用户信息")
    public HttpResult putUserInfo(@RequestBody @Valid UserAllInfoDto userAllInfo) {
        return userService.putUserAllInfo(userAllInfo);

    }

    @ApiOperation("修改手机号")
    @PutMapping("/mobile")
    public HttpResult mobile(@RequestBody ChangePhoneDto phoneDto) {
        return userService.changePhone(phoneDto);
    }

    @ApiOperation("修改密码")
    @PutMapping("/password")
    public HttpResult password(@RequestBody ChangePasswordDto changePasswordDto) {

        return userService.changePassword(changePasswordDto);

    }

    @ApiOperation("修改头像")
    @PutMapping("/portrait")
    public HttpResult upHeader(@RequestParam MultipartFile portrait) {
        return userService.upHeadPortrait(portrait);
    }

    @ApiOperation("获取修改手机号验证码")
    @GetMapping("/phone-verification")
    public HttpResult changePhoneVerification(@RequestParam String mobile) {
        return userService.changePhoneVerification(mobile);
    }

    @ApiOperation("获取修改密码验证码")
    @GetMapping("/password-verification")
    public HttpResult changePasswordVerification() {
        return userService.changePasswordVerification();
    }
}
