package com.example.security;

import java.io.Serializable;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;

/**
 * Permission evaluator. Works with @PreAuthorize("hasPermission(#event, 'createEvent')") 
 * 
 */
public class GoalPermissionsEvaluetor implements PermissionEvaluator {

    private DataSource datasource;  
    
    public DataSource getDatasource() {
        return datasource;
    }

    public void setDatasource(DataSource datasource) {
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
