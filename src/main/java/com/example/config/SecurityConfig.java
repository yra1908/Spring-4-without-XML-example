package com.example.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;

@Configuration
@EnableWebMvcSecurity
//@ImportResource({ "classpath:security-config.xml" })                     
//@EnableGlobalMethodSecurity(prePostEnabled = true)                      //Enable @PreAuthorize 
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired DataSource datasource;    
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        
        auth.jdbcAuthentication()
            .dataSource(datasource)
            .passwordEncoder(new BCryptPasswordEncoder());  
        
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {

      http          
          .authorizeRequests()          
          .antMatchers("/loginFailed.html", "/403.html", "/logout.html", "/login.html").permitAll()
          .antMatchers("/**").access("hasRole('ROLE_USER')")
          .antMatchers("/getEvents.html").access("hasRole('ROLE_ADMIN')")
          .anyRequest().authenticated()
          .and()
              .formLogin().loginPage("/login.html").failureUrl("/login.html?error").defaultSuccessUrl("/index.html")               
          .and()
              .logout().logoutSuccessUrl("/logout.html")  
          .and()
              .exceptionHandling().accessDeniedPage("/403.html");  
        
    }
    
    
    
}
