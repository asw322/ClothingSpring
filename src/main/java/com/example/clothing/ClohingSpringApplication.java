package com.example.clothing;

import java.util.*;

import com.example.clothing.NetworkRequest.Request_HM;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;



@SpringBootApplication
public class ClohingSpringApplication {

	// Private data fields	
	private static ApplicationContext CONTEXT = new AnnotationConfigApplicationContext(AppConfig.class);
	private static ClientInterface CLIENT;

	@Autowired
	private static Input INPUT;

	private static void terminalApp() {
		// Instantiate Scanner
		INPUT = CONTEXT.getBean(Input.class);

		System.out.println("Welcome to Clothing Software with Spring Framework!");
		System.out.println("Select an option: ");
		System.out.println("1. Login");
		System.out.println("2. Create a new Account");


		// Get user input
		String action; 
		while(true) {
			action = INPUT.getString();
			System.out.println();

			if(action.equals("1") || action.equals("2")) {
				break;
			}
			else {
				System.out.println("Invalid input");
				System.out.println("Select an option: ");
				System.out.println("1. Login");
				System.out.println("2. Create a new Account\n");
			}
		}
		
		CLIENT = CONTEXT.getBean(ClientInterface.class);

		if(action.equals("1")) {
			CLIENT.login();
		}
		else if(action.equals("2")) {
			CLIENT.createAccount();
		}

		CLIENT.showPortfolio();
		
		// while(true) {
			// CLIENT.showPortfolio();
		// }
		// System.out.println("hello");
	}


	private static void insertHM() {
		Request_HM hmObj = new Request_HM();
		// hmObj.parseData();
		hmObj.tester();
	}


	public static void main(String[] args) {
		SpringApplication.run(ClohingSpringApplication.class, args);		

		// terminalApp();
		// insertHM();
	}
}
