package com.weiyx.nos.controller;

import com.weiyx.nos.enums.UserTypeEnum;
import com.weiyx.nos.model.LoginUser;
import com.weiyx.nos.model.Result;
import com.weiyx.nos.service.UserLoginService;
import com.weiyx.nos.utils.LoginAuthorizationUtils;
import com.weiyx.nos.vo.LoginDetailVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.http.Header;
import org.apache.http.HttpRequest;
import org.apache.http.client.methods.HttpHead;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "用户登录",tags = "用户登录、登出")
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserLoginService userLoginService;

    @ApiOperation("管理员用户登录（使用账号登录）登录后使用Head：Authorization:Bearer 认证")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名称"),
            @ApiImplicitParam(name = "password", value = "用户的密码"),
    })
    @PostMapping("/sys")
    public Result<LoginDetailVo> sysUserLogin(@RequestParam("username")String username, @RequestParam("password")String password){
        return Result.success(userLoginService.loginByPassword(username,password, UserTypeEnum.SYSTEM));
    }
    @ApiOperation("客户用户登录（使用手机号、邮箱登录）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "account", value = "用户名称"),
            @ApiImplicitParam(name = "password", value = "用户的密码"),
    })
    @PostMapping("/cus")
    public Result<LoginDetailVo> cusUserLogin(@RequestParam("account")String account,@RequestParam("password")String password){
        return Result.success(userLoginService.loginByPassword(account,password,UserTypeEnum.CUSTOMER));
    }

    @ApiOperation("用户退出登录")
    @PostMapping("logout")
    public Result logout(HttpRequest request){
        Header header = request.getFirstHeader(HttpHeaders.AUTHORIZATION);
        if(header == null){
            return Result.success();
        }
        String token=header.getValue().replace("Bearer ", "");
        userLoginService.logout(token);
        return Result.success();
    }


}
