package com.weiyx.nos.controller;

import com.weiyx.nos.enums.UserTypeEnum;
import com.weiyx.nos.model.Result;
import com.weiyx.nos.service.UserLoginService;
import com.weiyx.nos.vo.LoginDetailVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "用户登录")
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserLoginService userLoginService;

    @ApiOperation("管理员用户登录（使用账号登录）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名称"),
            @ApiImplicitParam(name = "password", value = "用户的密码"),
    })
    @PostMapping("/sys")
    public Result<LoginDetailVo> sysUserLogin(String username,String password){
        return Result.success(userLoginService.loginByPassword(username,password, UserTypeEnum.SYSTEM));
    }
    @ApiOperation("客户用户登录（使用手机号、邮箱登录）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "account", value = "用户名称"),
            @ApiImplicitParam(name = "password", value = "用户的密码"),
    })
    @PostMapping("/cus")
    public Result cusUserLogin(String account,String password){
        userLoginService.loginByPassword(account,password,UserTypeEnum.CUSTOMER);
        return Result.success();
    }


}
