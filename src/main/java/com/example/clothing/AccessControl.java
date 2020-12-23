package com.example.clothing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccessControl {
    // Private data fields

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

    public AccessControl() {
        INVALID_LOGIN_ATTEMPTS = 0;
        MAX_INVALID_LOGIN_ALLOWED = 5;
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

        // MAKES THE DATABASE CALLS TO SEE IF IT MATCHES 


        return USERTOKEN;
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
}
