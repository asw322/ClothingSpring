/**
 * NOTE: 
 * Configuration files create instances of singleton beans defined in this class
 * and does not recreate an new instance whenever a ContextApplication.getBean() call is made
 */


package com.example.clothing;

import java.util.*;

import com.example.clothing.DAO.Personality_DAO.PersonalityDataAccessService;
import com.example.clothing.DAO.User_DAO.UserDataAccessService;
import com.example.clothing.DAO.Clothing_DAO.ClothingDataAccessService;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

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





    @Bean
    @Primary
    public UserDataAccessService getDataAccessService() {
        return new UserDataAccessService();
    }

    @Bean
    @Primary
    public PersonalityDataAccessService getPersonalityDataAccessService() {
        return new PersonalityDataAccessService();
    }

    @Bean
    @Primary
    public ClothingDataAccessService getClothingDataAccessService() {
        return new ClothingDataAccessService();
    }


    @Bean
    @Primary
    public ProcessingInterface getProcessingInterface() {
        return new ProcessingInterface();
    }

    // @Bean
    // @Primary
    // public DriverManagerDataSource getDriverManagerDataSource() {
    //     DriverManagerDataSource dataSource = new DriverManagerDataSource();

    //     // Set connection properties
    //     dataSource.setDriverClassName("org.postgresql.Driver"); 
    //     dataSource.setUrl("jdbc:postgresql://localhost:5432/demodb");
    //     dataSource.setUsername("postgres");
    //     dataSource.setPassword("password");

    //     return dataSource;
    // }

    // @Bean
    // @Primary
    // public JdbcTemplate getJdbcTemplate() {
    //     return new JdbcTemplate(null);
    // }
}
