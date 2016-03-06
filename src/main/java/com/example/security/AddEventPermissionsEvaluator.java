package com.example.security;

import java.io.Serializable;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import com.example.config.PersistanceConfig;
import com.example.config.WebConfig;

/**
 * Permission evaluator. Works with @PreAuthorize("hasPermission(#event, 'createEvent')")  
 */
@Component
public class AddEventPermissionsEvaluator implements PermissionEvaluator {
    
    
    private DataSource datasource;
    
    @Autowired
    public void setDataSource(DataSource datasource) {
        this.datasource = datasource;
    }
    
    @Override    
    public boolean hasPermission(Authentication auth, Object targetDomainObject, Object permission) {
        
        JdbcTemplate template = new JdbcTemplate(datasource);        
        
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
}

/*
 SQL for permissions table
 
 create table permissions (
    username varchar(50) not null,
    target varchar(50) not null,
    permission varchar(50) not null,
    constraint fk_permissions_users foreign key(username) references users(username));
    
create unique index ix_perm_username on permissions (username, target, permission);

insert into permissions (username, target, permission) 
    values ("admin", "com.example.model.Event", "createEvent");
    
*/
