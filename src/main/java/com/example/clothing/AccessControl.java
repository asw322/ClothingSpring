package com.example.clothing;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccessControl {
    // Private data fields
    @Autowired
    private Scanner CONSOLE;

    @Autowired
    private Encrypt ENCRYPT;

    private int INVALID_LOGIN_ATTEMPTS;
    private int MAX_INVALID_LOGIN_ALLOWED;

    private String USERNAME;
    private String PASSWORD;
    private String HASHEDPASSWORD;

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
        while(true) {
            System.out.print("Email or Username: ");
            USERNAME = CONSOLE.nextLine();
            if(USERNAME.length() > 0) {
                break;
            }
        }

        // Getting password
        while(true) {
            System.out.print("Password: ");
            PASSWORD = CONSOLE.nextLine();
            if(PASSWORD.length() > 0) {
                break;
            }
        }
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
        System.out.println("creating new account");
    }
}
