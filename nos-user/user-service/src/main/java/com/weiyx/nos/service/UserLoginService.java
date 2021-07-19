package com.weiyx.nos.service;

import com.weiyx.nos.vo.LoginDetailVo;

public interface UserLoginService {
    LoginDetailVo loginByUsername(String username ,String password,String loginType);
}
