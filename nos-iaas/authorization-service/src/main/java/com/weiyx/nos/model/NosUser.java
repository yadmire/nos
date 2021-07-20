package com.weiyx.nos.model;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.*;

/**
 * @author ：weiyuxin
 * @date ：2021/7/20
 */
public class NosUser extends User {
    private Integer userId;

    public Integer getUserId() {
        return userId;
    }

    public NosUser(Integer userId,String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.userId=userId;
    }
}
