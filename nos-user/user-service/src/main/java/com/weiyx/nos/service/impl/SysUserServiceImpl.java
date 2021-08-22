package com.weiyx.nos.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.weiyx.nos.vo.SysUserVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weiyx.nos.model.SysUser;
import com.weiyx.nos.mapper.SysUserMapper;
import com.weiyx.nos.service.SysUserService;
import org.springframework.util.CollectionUtils;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService{

    @Override
    public Page<SysUserVo> getUsers(Page<SysUser> page, String phone, String username) {
        Page<SysUser> pageData = page(page,
                new LambdaQueryWrapper<SysUser>()
                        .like(!StringUtils.isEmpty(phone), SysUser::getPhone, phone)
                        .like(!StringUtils.isEmpty(username), SysUser::getUsername, username)

        );
        List<SysUser> records= pageData.getRecords();

        Page<SysUserVo> result =new Page<>(pageData.getCurrent(),pageData.getSize(),pageData.getTotal());
        if(!CollectionUtils.isEmpty(records)){
            result.setRecords(records.stream().map(sysUser -> {
                SysUserVo sysUserVo= new SysUserVo();
                BeanUtils.copyProperties(sysUser,sysUserVo);
                return sysUserVo;
            }).collect(Collectors.toList()));
        }
        return result;
    }
}
