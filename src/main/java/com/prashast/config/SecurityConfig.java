package com.prashast.config;

import com.prashast.service.UserModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity
@Profile("live")
@Order(1)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserModelService userModelService;

    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userModelService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                authorizeRequests().
                    antMatchers("/rest/secured/*").hasRole("USER").
                    antMatchers("/rest/admin").hasRole("ADMIN").
                    antMatchers("/rest/unsecured").permitAll().
                    antMatchers("/index.html").hasRole("USER").
                    antMatchers("/index.jsp").permitAll().
                and().
                formLogin().
                    loginPage("/logon.html").
                    loginProcessingUrl("/j_spring_security_check").
                    usernameParameter("j_username").
                    passwordParameter("j_password").
                    defaultSuccessUrl("/index.html").
                    failureUrl("/logonError.html").
                    permitAll().
                and().
                    csrf().disable();
    }
}

