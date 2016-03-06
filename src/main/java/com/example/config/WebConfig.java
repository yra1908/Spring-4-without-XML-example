package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@PropertySource(value = { "classpath:application.properties" })
@ComponentScan(basePackages="com.example")
public class WebConfig extends WebMvcConfigurerAdapter { //extend WebMvcConfigurerAdapter for controll static resources (like pdf files)
    
    @Bean
    public InternalResourceViewResolver getInternalResourceViewResolver(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/jsp/");
        resolver.setSuffix(".jsp");
        return resolver;
    }
    
    /**
     * We extend WebMvcConfigurerAdapter and rewrite this 
     * method to use our static resources(in this example 
     * pdf files in "/WEB-INF/pdf/" dir)
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        
        registry.addResourceHandler("/pdfs/**").addResourceLocations("/WEB-INF/pdf/");
        
        //another resource example without implementation
        registry.addResourceHandler("/css/**").addResourceLocations("/WEB-INF/css/");
    }
    
}
