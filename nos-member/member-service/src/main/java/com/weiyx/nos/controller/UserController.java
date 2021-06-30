package com.weiyx.nos.controller;

import com.weiyx.nos.model.Result;
import com.weiyx.nos.model.SysUser;
import com.weiyx.nos.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private SysUserService sysUserService;



    @GetMapping("test")
    public String test(){
        return "ok";
    }

    @GetMapping("{id}")
    public Result<SysUser> getUser(@PathVariable("id") Integer id){

        return Result.success(sysUserService.selectByPrimaryKey(id));
    }
}
