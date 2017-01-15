package com.prashast.service;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StubbedUserModelService implements AuthenticationProvider {

    private final static String STUBBED_PASSWORD = "P@ssw0rd";

    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        if(password.equalsIgnoreCase(STUBBED_PASSWORD)){
            List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
            SimpleGrantedAuthority roleUserAuthority = new SimpleGrantedAuthority("ROLE_USER");
            authorities.add(roleUserAuthority);
            return new UsernamePasswordAuthenticationToken(name,password,authorities);
        }
        return null;
    }

    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
