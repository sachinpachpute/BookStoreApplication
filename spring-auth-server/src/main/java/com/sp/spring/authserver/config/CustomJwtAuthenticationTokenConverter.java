package com.sp.spring.authserver.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.List;

public class CustomJwtAuthenticationTokenConverter implements Converter<Jwt, JwtAuthenticationToken> {

    @Override
    public  JwtAuthenticationToken convert(Jwt source){
        List<String> authorities = (List<String>) source.getClaims().get("roles");
        String userName = source.getClaimAsString("user_name");
        JwtAuthenticationToken authenticationObject =
                new JwtAuthenticationToken(source, authorities.stream().map(SimpleGrantedAuthority::new).toList(), userName);
        return authenticationObject;
    }
}
