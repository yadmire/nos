package com.weiyx.nos.controller;

import com.weiyx.nos.model.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * 角色管理
 *
 * @author ：weiyuxin
 * @date ：2021/7/23
 */
@RestController
@Api(tags = "用户角色接口")
public class RoleController {

    @ApiOperation("获取角色")
    @GetMapping
    public Result getRols(){
        return Result.success();
    }

    @ApiOperation("新增角色")
    @PostMapping
    public Result addRole(){
        return Result.success();
    }

    @ApiOperation("根据id修改角色")
    @PutMapping
    public Result editRole(){
        return Result.success();
    }

    @ApiOperation("删除角色")
    @DeleteMapping
    public Result deleteRole(){
        return Result.success();
    }
}
