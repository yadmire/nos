package com.weiyx.nos.config.resource;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.token.UserAuthenticationConverter;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author ：weiyuxin
 * @date ：2021/7/20
 */
public class OauthUserAuthenticationConverter implements UserAuthenticationConverter {
    private Collection<? extends GrantedAuthority> defaultAuthorities;


    public OauthUserAuthenticationConverter() {
    }
    public void setDefaultAuthorities(String[] defaultAuthorities) {
        this.defaultAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(StringUtils.arrayToCommaDelimitedString(defaultAuthorities));
    }

    @Override
    public Map<String, ?> convertUserAuthentication(Authentication authentication) {
        Map<String, Object> response = new LinkedHashMap();
        response.put("user_name", authentication.getName());
        if (authentication.getAuthorities() != null && !authentication.getAuthorities().isEmpty()) {
            response.put("authorities", AuthorityUtils.authorityListToSet(authentication.getAuthorities()));
        }

        return response;
    }

    @Override
    public Authentication extractAuthentication(Map<String, ?> map) {
        if (map.containsKey("user_name")) {
            Map principalMap =new LinkedHashMap();
            principalMap.put("user_name",map.get("user_name"));
            principalMap.put("user_id",map.get("user_id"));

            Object principal = principalMap;

            Collection<? extends GrantedAuthority> authorities = this.getAuthorities(map);

            return new UsernamePasswordAuthenticationToken(principal, "N/A", authorities);
        } else {
            return null;
        }
    }
    private Collection<? extends GrantedAuthority> getAuthorities(Map<String, ?> map) {
        if (!map.containsKey("authorities")) {
            return this.defaultAuthorities;
        } else {
            Object authorities = map.get("authorities");
            if (authorities instanceof String) {
                return AuthorityUtils.commaSeparatedStringToAuthorityList((String)authorities);
            } else if (authorities instanceof Collection) {
                return AuthorityUtils.commaSeparatedStringToAuthorityList(StringUtils.collectionToCommaDelimitedString((Collection)authorities));
            } else {
                throw new IllegalArgumentException("Authorities must be either a String or a Collection");
            }
        }
    }
}
