/**
 * NOTE: 
 * Configuration files create instances of singleton beans defined in this class
 * and does not recreate an new instance whenever a ContextApplication.getBean() call is made
 */


package com.example.clothing;

import java.util.*;


import org.flywaydb.core.internal.jdbc.JdbcTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
// import org.springframework.jdbc.datasource.*;

@Configuration
public class AppConfig {
    @Bean
    @Primary
    public Scanner getScannerIn() {
        return new Scanner(System.in);
    }

    @Bean
    @Primary
    public Input getInput() {
        return new Input();
    }

    @Bean
    @Primary
    public ClientInterface getClientInterface() {
        return new ClientInterface();
    }

    @Bean
    @Primary
    public AccessControl getAccessControl() {
        return new AccessControl();
    }

    @Bean
    @Primary
    public Encrypt getEncrypt() {
        return new Encrypt();
    }

    @Bean
    @Primary
    public UserToken getUserToken() {
        return new UserToken();
    }

    // @Bean
    // @Primary
    // public JdbcTemplate getJdbcTemplate() {
    //     return new JdbcTemplate(null);
    // }
}
