package com.weiyx.nos.vo;

import com.weiyx.nos.model.SysUserDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

/**
 * @author ：weiyuxin
 * @date ：2021/7/19
 */
@Data
@AllArgsConstructor
public class LoginDetailVo {
    private String accessToken;
    private SysUserDetails userDetails;
    private String refreshToken;
    private List<SimpleGrantedAuthority> authorityList;
}
