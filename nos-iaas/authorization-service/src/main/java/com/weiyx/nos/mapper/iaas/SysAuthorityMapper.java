package com.weiyx.nos.mapper.iaas;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.weiyx.nos.model.iaas.SysAuthority;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ：weiyuxin
 * @date ：2021/7/12
 */
@Repository
public interface SysAuthorityMapper extends BaseMapper<SysAuthority> {
    List<String> getUserAuthorityByUid(Integer uid);
}
