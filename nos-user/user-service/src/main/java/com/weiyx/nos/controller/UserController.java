package com.weiyx.nos.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.weiyx.nos.costant.ErrorCodeEnum;
import com.weiyx.nos.model.Result;
import com.weiyx.nos.model.SysUser;
import com.weiyx.nos.service.SysUserService;
import com.weiyx.nos.vo.SysUserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/accounts")
@Api(value = "用户账号接口",tags ="用户账号接口")
public class UserController {
    @Autowired
    private SysUserService sysUserService;



    @ApiOperation(value = "根据ID获取用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "用户id")
    }
    )
    @GetMapping("/id/{id}")
    public Result<SysUserVo> getUSer(@PathVariable("id") Long id){
        SysUser sysUser=sysUserService.getById(id);
        SysUserVo sysUserVo= new SysUserVo();
        BeanUtils.copyProperties(sysUser,sysUserVo);
        return Result.success(sysUserVo);
    }

    @ApiOperation(value = "查询用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current" ,value = "当前页") ,
            @ApiImplicitParam(name = "size" ,value = "每页显示的条数") ,
            @ApiImplicitParam(name = "phone" ,value = "员工的手机号码") ,
            @ApiImplicitParam(name = "username" ,value = "用户名") ,
    })
    @GetMapping
    public Result<Page<SysUserVo>>getUsers(@ApiIgnore Page<SysUser> page , String phone , String username){
        Page<SysUserVo> pageData=sysUserService.getUsers(page,phone,username);
        return Result.success(pageData);
    }

    @ApiOperation(value = "用户注册")
    @PostMapping("/register")
    public Result register(){
        return Result.success();
    }

    @ApiOperation(value = "管理员后台新增用户")
    @PostMapping
    @PreAuthorize("hasAuthority('user_edit')")
    public Result add(){
        return Result.success();
    }

    /**
     * 用户修改密码
     * @return
     */
    @ApiOperation(value = "修改密码")
    @PatchMapping("password")
    public Result updatePassword(){
        return Result.success();
    }

    @ApiOperation(value = "用户自行注销")
    @DeleteMapping
    public Result deleteSelfUser(){
        return Result.success();
    }

    @ApiOperation(value = "通过用户id注销")
    @DeleteMapping("/id/{id}")
    public Result deleteUserById(@PathVariable("id") Long id){
        return Result.success();
    }

}
