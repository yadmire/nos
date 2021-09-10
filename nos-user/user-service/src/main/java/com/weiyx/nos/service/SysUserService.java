package com.weiyx.nos.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.weiyx.nos.model.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weiyx.nos.vo.SysUserVo;

public interface SysUserService extends IService<SysUser>{


    Page<SysUserVo> getUsers(Page<SysUser> page, String phone, String username);

    boolean addUser(SysUser user);
}
