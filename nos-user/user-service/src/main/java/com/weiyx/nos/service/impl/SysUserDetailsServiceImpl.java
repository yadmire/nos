package com.weiyx.nos.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weiyx.nos.mapper.SysUserDetailsMapper;
import com.weiyx.nos.model.SysUserDetails;
import com.weiyx.nos.service.SysUserDetailsService;
@Service
public class SysUserDetailsServiceImpl extends ServiceImpl<SysUserDetailsMapper, SysUserDetails> implements SysUserDetailsService{

}
