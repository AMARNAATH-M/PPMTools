//package io.agileintellligence.fullstack.security;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.*;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//@Configuration
//@EnableWebSecurity
//public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {
//
//
//    @Override
//     public void configure(HttpSecurity http)throws Exception {
//         http
//                 .csrf().disable()
//                 .authorizeRequests()
//                 .anyRequest()
//                 .authenticated()
//                 .and()
//                 .httpBasic();
//     }
//}
