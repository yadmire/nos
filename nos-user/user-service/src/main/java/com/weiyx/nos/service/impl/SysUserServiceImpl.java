package com.weiyx.nos.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weiyx.nos.model.SysUser;
import com.weiyx.nos.mapper.SysUserMapper;
import com.weiyx.nos.service.SysUserService;
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService{

}
