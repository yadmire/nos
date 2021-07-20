package com.weiyx.nos.controller;

import com.weiyx.nos.costant.ErrorCodeEnum;
import com.weiyx.nos.model.Result;
import com.weiyx.nos.model.SysUser;
import com.weiyx.nos.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
@Api(value = "用户账号接口")
public class UserController {
    @Autowired
    private SysUserService sysUserService;



    @ApiOperation(value = "根据ID获取用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "用户id")
    }
    )
    @GetMapping("/id/{id}")
    public Result<SysUser> getUSer(@PathVariable("id") Long id){
        SysUser sysUser=sysUserService.getById(id);
        return Result.success(sysUser);
    }

    @ApiOperation(value = "用户注册")
    @PostMapping("/register")
    public Result register(){
        return Result.success();
    }
    @ApiOperation(value = "新增用户")
    @PostMapping
    @PreAuthorize("hasAuthority('user_edit')")
    public Result add(){
        return Result.success();
    }
    @ApiOperation(value = "修改密码")
    @PatchMapping("password")
    public Result updatePassword(){
        return Result.success();
    }



}
