package com.example.clothing;

// import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClientInterface {
    
    @Autowired
    private AccessControl CONTROL;

    @Autowired
    private Input INPUT;

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
        printHeader("My Portfolio");

        String action;

        while(true) {
            action = INPUT.getRobustInput("Select your action:\n1. See Outfit Collection\n2. Recommend An Outfit\n3. Settings\n4. Exit\nEnter: ");
            if(action.equals("1") || action.equals("2") || action.equals("3") || action.equals("4")) {
                break;
            }
        }

        int intAction = Integer.parseInt(action);

        // See outfit collection
        if(intAction == 1) {
            seeCollection();
        }
        // Recommend an outfit
        else if(intAction == 2) {
            recOutfit();
        }
        // Settings
        else if(intAction == 3) {
            settings();
        }
        else if(intAction == 4) {
            System.out.println("Thanks for using Clothing Spring..Bye");
            System.exit(2);
        }
    }

    private void seeCollection() {
        printHeader("Showing Collection");
    }

    private void recOutfit() {
        printHeader("Recommending Outfit");
    }

    private void settings() {
        printHeader("Settings");
    }

    public void printHeader(String headerName) {
        System.out.println("\n");
        System.out.println(headerName);
        System.out.println("--------------------------");
    }
}
