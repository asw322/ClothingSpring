package com.example.clothing;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


@SpringBootApplication
public class ClohingSpringApplication {

	// Private data fields
	@Autowired
	private static Input INPUT;
	private static ApplicationContext CONTEXT = new AnnotationConfigApplicationContext(AppConfig.class);
	private static ClientInterface CLIENT;

	public static void main(String[] args) {
		// Uncomment later!
		// SpringApplication.run(ClohingSpringApplication.class, args);		

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
		

		// System.out.println("You entered = " + action);

		if(action.equals("1")) {
			CLIENT = CONTEXT.getBean(ClientInterface.class);
			CLIENT.test();
		}

	}

}
