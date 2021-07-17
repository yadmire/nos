package com.weiyx.nos.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weiyx.nos.model.SysChangeLog;
import com.weiyx.nos.mapper.SysChangeLogMapper;
import com.weiyx.nos.service.SysChangeLogService;
@Service
public class SysChangeLogServiceImpl extends ServiceImpl<SysChangeLogMapper, SysChangeLog> implements SysChangeLogService{

}
