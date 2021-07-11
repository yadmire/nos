package com.weiyx.nos.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.weiyx.nos.model.SysUser;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.weiyx.nos.mapper")
public interface SysUserMapper extends BaseMapper<SysUserMapper> {
    public SysUser getuserByUsername(String username, String tenantCode);
}
