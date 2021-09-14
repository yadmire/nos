package com.weiyx.nos.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.enums.ApiErrorCode;
import com.baomidou.mybatisplus.extension.exceptions.ApiException;
import com.weiyx.nos.costant.ErrorCodeEnum;
import com.weiyx.nos.enums.UserTypeEnum;
import com.weiyx.nos.feignclient.Oauth2FeignClient;
import com.weiyx.nos.model.JwtToken;
import com.weiyx.nos.model.NosException;
import com.weiyx.nos.model.SysUser;
import com.weiyx.nos.service.SysUserService;
import com.weiyx.nos.service.UserLoginService;
import com.weiyx.nos.vo.LoginDetailVo;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@Slf4j
public class USerLoginServiceImpl implements UserLoginService {

    @Value("${basic.token:Basic bm9zLWFwaTpub3Mtc2VjcmV0}")
    String basicToken;

    @Autowired
    private Oauth2FeignClient oauth2FeignClient;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private SysUserService sysUserService;





    @Override
    public LoginDetailVo loginByPassword(String account, String password,UserTypeEnum userType) {
        String username=account;
        if(userType==UserTypeEnum.CUSTOMER ){
            QueryWrapper<SysUser> userWrapper=new QueryWrapper<>();
            userWrapper.eq("email",account).or().eq("phone",account);
            SysUser user=sysUserService.getOne(userWrapper);
            username=user !=null? user.getUsername():account;
        }
        ResponseEntity<JwtToken> responseEntity;
        try{
             responseEntity=oauth2FeignClient.getToken("password",username,password,"password",basicToken);
        }catch (FeignException e){
            log.error(e.getMessage());
            throw new NosException(ErrorCodeEnum.INVALID_GRANT);
        }

        if (responseEntity.getStatusCode() != HttpStatus.OK) {
            throw new ApiException(ApiErrorCode.FAILED);
        }
        JwtToken jwtToken = responseEntity.getBody();
        String token= jwtToken.getAccessToken();
        Jwt jwt= JwtHelper.decode(token);
        JSONObject jwtJson = JSON.parseObject(jwt.getClaims());
        String userId=jwtJson.getString("user_id");
        // 获取用户的权限数据 token 里面有直接取出
        JSONArray authoritiesJsonArray = jwtJson.getJSONArray("authorities");
        // 进行数据对象的转换
        List<SimpleGrantedAuthority> authorities = authoritiesJsonArray.stream().map(authorityJson -> new SimpleGrantedAuthority(authorityJson.toString())).collect(Collectors.toList());
        redisTemplate.opsForValue().set(token, userId, jwtToken.getExpiresIn(), TimeUnit.SECONDS);
        LoginDetailVo loginDetailVo=new LoginDetailVo(token,null,jwtToken.getRefreshToken(),authorities);
        return loginDetailVo;
    }

    @Override
    public Boolean logout(String token) {
        return  redisTemplate.delete(token);

    }
}
