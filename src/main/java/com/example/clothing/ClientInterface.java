package com.example.clothing;

// import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClientInterface {
    
    @Autowired
    private AccessControl CONTROL;

    @Autowired
    private UserToken USERTOKEN;

    public ClientInterface() {
        System.out.println("Client Interface Instance Created");
    }  

    /**
     * Login through AccessControl
     */
    public void login() {
        USERTOKEN = CONTROL.login();
    }

    /**
     * Create a new account through AccessControl
     */
    public void createAccount() {
        USERTOKEN = CONTROL.createAccount();
    }

    /**
     * Show the user's portfolio
     */
    public void showPortfolio() {

    }
}
