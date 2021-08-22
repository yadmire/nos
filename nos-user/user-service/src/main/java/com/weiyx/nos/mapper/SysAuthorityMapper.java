package com.weiyx.nos.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.weiyx.nos.model.SysAuthority;

import java.util.List;

public interface SysAuthorityMapper extends BaseMapper<SysAuthority> {
     List<SysAuthority> searchByid(String id);
}