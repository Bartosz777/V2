package com.crud.restapp.tasks.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().and().authorizeRequests()
                .antMatchers(HttpMethod.POST, "/v1/users").permitAll()
                .antMatchers(HttpMethod.GET,"/v1/users").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET,"/v1/users/{userId}").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/v1/users/{userId}").hasAnyAuthority("ADMIN", "USER")
                .antMatchers(HttpMethod.PUT, "/v1/users").hasAnyAuthority("ADMIN", "USER")
                .antMatchers(HttpMethod.POST, "/v1/tasks").hasAnyAuthority("ADMIN", "USER")
                .antMatchers(HttpMethod.GET, "/v1/tasks").hasAnyAuthority("ADMIN", "USER")
                .antMatchers(HttpMethod.GET,"/v1/tasks/{taskId}").hasAnyAuthority("ADMIN", "USER")
                .antMatchers(HttpMethod.DELETE, "/v1/tasks/{taskId}").hasAnyAuthority("ADMIN", "USER")
                .antMatchers(HttpMethod.PUT, "/v1/tasks").hasAnyAuthority("ADMIN", "USER")
                .and()
                .formLogin().permitAll()
                .and()
                .csrf().disable();
    }

}
