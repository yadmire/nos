package com.weiyx.nos.service;

import com.weiyx.nos.enums.UserTypeEnum;
import com.weiyx.nos.vo.LoginDetailVo;

public interface UserLoginService {
    LoginDetailVo loginByPassword(String account , String password, UserTypeEnum userType);

    Boolean logout(String token);
}
