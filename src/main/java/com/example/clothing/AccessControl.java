package com.example.clothing;

import java.beans.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;

import java.util.*;

// import javax.sql.DataSource;

import org.flywaydb.core.internal.database.base.Connection;
// import org.flywaydb.core.internal.jdbc.JdbcTemplate;


// import java.sql.Connection;
import org.springframework.jdbc.core.JdbcTemplate;


import org.springframework.jdbc.core.RowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Component
public class AccessControl {
    // Private data fields

    // @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private Encrypt ENCRYPT;

    @Autowired 
    private Input INPUT;

    @Autowired 
    private UserToken USERTOKEN;

    private int INVALID_LOGIN_ATTEMPTS;
    private int MAX_INVALID_LOGIN_ALLOWED;

    private String USERNAME;
    private String PASSWORD;
    private String HASHEDPASSWORD;

    private String NAME;
    private String BIRTHDATE;
    private String SEX;
    private String ID;

    @Autowired
    public AccessControl() {
        INVALID_LOGIN_ATTEMPTS = 0;
        MAX_INVALID_LOGIN_ALLOWED = 5;

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver"); // May need to change 
        dataSource.setUrl("jdbc:postgresql://localhost:5432/demodb");
        dataSource.setUsername("postgres");
        dataSource.setPassword("password");
        

        // Establish connection with JDBC 
        // try {
            // DataSource dataSource = new DataSource();
            // jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate = new JdbcTemplate(dataSource);
            // jdbcTemplate = new JdbcTemplate(DriverManager.getConnection("jdbc:postgresql://localhost:5432/demodb"));
        // } catch(SQLException e) {
            // e.printStackTrace();
        // }
    }

    // LOGIN
    public UserToken login() {
        if(INVALID_LOGIN_ATTEMPTS == MAX_INVALID_LOGIN_ALLOWED) {
            System.out.println("Max attempts reached");
            System.exit(1);
        }
        if(INVALID_LOGIN_ATTEMPTS == MAX_INVALID_LOGIN_ALLOWED - 1) {
            System.out.println("One more attempt allowed");
        }

        // Attempt login
        System.out.println("Sign in");

        // Getting Username
        USERNAME = this.getRobustInput("Email or Username: ");

        // Getting password
        PASSWORD = this.getRobustInput("Password: ");
        System.out.println("Input password = " + PASSWORD);

        // Hash the password
        HASHEDPASSWORD = ENCRYPT.getHash(PASSWORD);
        System.out.println("Output hash = " + HASHEDPASSWORD);

        // Remove password
        PASSWORD = ""; 

        // Check if getHash function failed
        if(HASHEDPASSWORD.equals("null")) {
            INVALID_LOGIN_ATTEMPTS++;
            System.out.println("Error with hashing password");
            login();
        }

        List<UserToken> elem = getAllPeople();

        for(int i = 0; i < elem.size(); i++) {
            elem.get(i).printUserToken();
        }

        return null;
    }

    // CREATING NEW ACCOUNT
    public UserToken createAccount() {
        System.out.println("Creating New Account");

        // Getting Username
        USERNAME = this.getRobustInput("Email or Username: ");

        // Getting password
        PASSWORD = this.getRobustInput("Password: ");
        System.out.println("Input password = " + PASSWORD);

        // Confirming password
        while(true) {
            String PASSWORD2 = INPUT.getString("Re-enter Password: ");
            if(PASSWORD.equals(PASSWORD2)) {
                break;
            }
            else {
                System.out.println("Passwords do not match");
                createAccount();
            }
        }

        // Hash password
        HASHEDPASSWORD = ENCRYPT.getHash(PASSWORD);
        System.out.println("Output hash = " + HASHEDPASSWORD);

        // Remove password
        PASSWORD = ""; 

        // Create a User account
        System.out.println("Creating your account..");
        System.out.println("Generating Unique ID: ");
        

        System.out.println("Account created..");
        System.out.println("Let's begin by inputting your basic information");
        
        NAME = this.getRobustInput("Enter your name: ");
        BIRTHDATE = this.getRobustInput("Enter your birthdate (YYYY-MM-DD): ");
        SEX = this.getRobustInput("Enter your gender: \n1. Male\n2. Female\nEnter: ");

        // Create a user token 
        this.createUserToken(USERNAME, HASHEDPASSWORD, NAME, BIRTHDATE, SEX, ID);
        
        // We can store this USERTOKEN into the DB 

        return USERTOKEN;
    }

    /**
     * 
     * @param _USERNAME
     * @param _HASHEDPASSWORD
     * @param _NAME
     * @param _BIRTHDATE
     * @param _SEX
     * @param _ID
     */
    private void createUserToken(String _USERNAME, String _HASHEDPASSWORD, String _NAME, String _BIRTHDATE, String _SEX, String _ID) {
        // Handle SEX parameter
        // Handle BIRTHDATE parameter
        String preprocessed_sex = this.checkSex(_SEX);
        String preprocessed_birthdate = this.checkBirthdate(_BIRTHDATE);
        USERTOKEN.setAll(_USERNAME, _HASHEDPASSWORD, _NAME, preprocessed_birthdate, preprocessed_sex, _ID);
    }

    /**
     * 
     * @param _BIRTHDATE
     * @return
     */
    private String checkBirthdate(String _BIRTHDATE) {
        // Need to implement this later
        // Check if the input is in the form YYYY-MM-DD
        // If not, transform _BIRTHDATE into that form
        // Return errors if appropriate 
        return null;
    }

    /**
     * 
     * @param _SEX
     * @return
     */
    private String checkSex(String _SEX) {
        if(SEX.equals("1") || SEX.toLowerCase().equals("male")) {
            return "1";
        }
        else if(SEX.equals("2") || SEX.toLowerCase().equals("female")) {
            return "2";
        }
        else {
            return null;
        }
    }


    /**
     * Handles Robust Inputs
     * @param str
     * @return
     */
    private String getRobustInput(String str) {
        String temp;
        while(true) {
            temp = INPUT.getString(str);
            if(temp.length() > 0) {
                break;
            }
        }
        return temp; 
    }

    public List<UserToken> getAllPeople() {
        System.out.println("Grabbing all users!");
        return jdbcTemplate.query("SELECT * FROM person", new RowMapper<UserToken>() {

            @Override
            public UserToken mapRow(ResultSet rs, int rowNumber) throws SQLException {
                System.out.println("Setting up new user!");
                UserToken temp = new UserToken();
                temp.setLogin(rs.getString("name"), rs.getString("hashedpassword"), rs.getString("id"));
                return temp;
            }
        });
    }
}
