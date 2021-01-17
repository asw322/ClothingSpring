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
import com.example.clothing.DAO.Clothing_DAO.ClothingDataAccessServiceOnProductID;
import com.example.clothing.DAO.Clothing_DAO.ClothingDataAccessServiceOnProductName;
import com.example.clothing.DAO.Clothing_DAO.ClothingDataAccessServiceOnProductReferenceNumber;
import com.example.clothing.DAO.Clothing_DAO.ClothingDataAccessServiceOnProductURL;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

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
    public ClothingDataAccessServiceOnProductID getClothingDataAccessServiceOnProductID() {
        return new ClothingDataAccessServiceOnProductID();
    }

    @Bean
    @Primary
    public ClothingDataAccessServiceOnProductReferenceNumber getClothingDataAccessServiceOnProductReferenceNumber() {
        return new ClothingDataAccessServiceOnProductReferenceNumber();
    }

    @Bean
    @Primary
    public ClothingDataAccessServiceOnProductName getClothingDataAccessServiceOnProductName() {
        return new ClothingDataAccessServiceOnProductName();
    }

    @Bean
    @Primary
    public ClothingDataAccessServiceOnProductURL getClothingDataAccessServiceOnProductURL() {
        return new ClothingDataAccessServiceOnProductURL();
    }

    @Bean
    @Primary
    public ProcessingInterface getProcessingInterface() {
        return new ProcessingInterface();
    }

    @Bean
    @Primary
    public DriverManagerDataSource getDriverManagerDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        // Set connection properties
        dataSource.setDriverClassName("org.postgresql.Driver"); 
        dataSource.setUrl("jdbc:postgresql://localhost:5432/demodb");
        dataSource.setUsername("postgres");
        dataSource.setPassword("password");

        return dataSource;
    }

    @Bean
    @Primary
    public JdbcTemplate getJdbcTemplate() {
        return new JdbcTemplate(getDriverManagerDataSource());
    }

    @Bean
    @Primary
    public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
        // this.getBean(DriverManagerDataSource.class) ????
        return new NamedParameterJdbcTemplate(getDriverManagerDataSource());
    }
}
