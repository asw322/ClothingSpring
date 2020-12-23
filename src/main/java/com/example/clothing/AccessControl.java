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

    private int INVALID_LOGIN_ATTEMPTS;
    private int MAX_INVALID_LOGIN_ALLOWED;

    private String USERNAME;
    private String PASSWORD;
    private String HASHEDPASSWORD;

    private String NAME;
    private String BIRTHDATE;
    private int SEX;
    private String ID;

    public AccessControl() {
        INVALID_LOGIN_ATTEMPTS = 0;
        MAX_INVALID_LOGIN_ALLOWED = 5;
    }

    // LOGIN
    public void login() {
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

        System.out.println("finishes");
        // MAKES THE DATABASE CALLS TO SEE IF IT MATCHES 
    }

    // CREATING NEW ACCOUNT
    public void createAccount() {
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
        
        while (true) {

        }

    }

    /**
     * Handles Robust Inputs
     * @param str
     * @return
     */
    public String getRobustInput(String str) {
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
