package com.example.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Trying to config JPA without xml file jpaContext.xml
 * @author konstr
 * example here -http://www.baeldung.com/2011/12/13/the-persistence-layer-with-spring-3-1-and-jpa/
 * https://github.com/sivaprasadreddy/sivalabs-blog-samples-code/blob/master/springmvc-datajpa-security-demo/src/main/java/com/sivalabs/springapp/config/PersistenceConfig.java
 */
@Configuration
@EnableTransactionManagement
@ComponentScan({"com.example"})
@EnableJpaRepositories(basePackages={"com.example.repository"})         //springData support
public class PersistanceConfig {
    
    @Autowired
    private Environment env;
    
    public PersistanceConfig() {
        super();
    }
    
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
       LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
       em.setDataSource(dataSource());
       em.setPackagesToScan(new String[] { "com.example.model" });
  
       JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
       em.setJpaVendorAdapter(vendorAdapter);
       em.setJpaProperties(additionalProperties());
  
       return em;
    }
  
    @Bean (name = "dataSource")
    public DataSource dataSource(){
       DriverManagerDataSource dataSource = new DriverManagerDataSource();
       dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
       dataSource.setUrl(env.getProperty("jdbc.url"));
       dataSource.setUsername(env.getProperty("jdbc.username"));
       dataSource.setPassword(env.getProperty("jdbc.password"));
       return dataSource;
    }
  
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf){
       JpaTransactionManager transactionManager = new JpaTransactionManager();
       transactionManager.setEntityManagerFactory(emf);
  
       return transactionManager;
    }
  
    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
       return new PersistenceExceptionTranslationPostProcessor();
    }
  
    Properties additionalProperties() {
       Properties properties = new Properties();
       properties.setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
       properties.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
       properties.setProperty("hibernate.format_sql", env.getProperty("hibernate.show_sql"));
       return properties;
    }

}
