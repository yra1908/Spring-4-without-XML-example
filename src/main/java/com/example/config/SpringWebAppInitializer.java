package com.example.config;

import javax.servlet.Filter;

import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class SpringWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] { WebConfig.class};
        
        //return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        
        return null;
    }

    @Override
    protected String[] getServletMappings() {        
        return new String[] { "/" };
    }
    
    @Override
    protected Filter[] getServletFilters() {
       return new Filter[]{
               new OpenEntityManagerInViewFilter()
            };
    }

}