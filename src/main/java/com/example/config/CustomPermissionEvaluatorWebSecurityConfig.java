package com.example.config;

import java.io.Serializable;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import com.example.security.AddEventPermissionsEvaluator;

@Configuration
@Component
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class CustomPermissionEvaluatorWebSecurityConfig extends GlobalMethodSecurityConfiguration {
    
    AddEventPermissionsEvaluator eventPermissionsEvaluator;
    
    @Autowired
    public void setAddEventPermissionsEvaluator(AddEventPermissionsEvaluator eventPermissionsEvaluator) {
        this.eventPermissionsEvaluator = eventPermissionsEvaluator;
    }
    
    @Override    
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        DefaultMethodSecurityExpressionHandler expressionHandler = new DefaultMethodSecurityExpressionHandler();
        expressionHandler.setPermissionEvaluator(eventPermissionsEvaluator);
        return expressionHandler;
    }
    
    
    /*alternative solution 
    @Autowired DataSource datasource;
    
    @Override    
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        DefaultMethodSecurityExpressionHandler expressionHandler = new DefaultMethodSecurityExpressionHandler();
        expressionHandler.setPermissionEvaluator(new PermissionEvaluator(){

            @Override
            public boolean hasPermission(Authentication auth, Object targetDomainObject, Object permission) {
                System.out.println();
                System.out.println("ping");   //now it's works. But it's still cant reference to another class with Permission
                                                //evaluator interface implementation. Problem with dataSource autowiring
                System.out.println();
                JdbcTemplate template = new JdbcTemplate(dataSource);
                //JdbcTemplate template = new JdbcTemplate(new PersistanceConfig().dataSource());
                
                Object [] args = {((User)auth.getPrincipal()).getUsername(), 
                        targetDomainObject.getClass().getName(), 
                        permission.toString()};
                
                int count = template.queryForObject("select count(*) from permissions p where "
                        + "p.username = ? and p.target = ? and p.permission = ?", args, Integer.class);
                
                if (count==1){
                    return true;
                } else {
                    return false;            
                }
            }

            @Override
            public boolean hasPermission(Authentication arg0, Serializable arg1, String arg2, Object arg3) {
                // TODO Auto-generated method stub
                return false;
            }
            
        });
        return expressionHandler;
    }*/
    
}
