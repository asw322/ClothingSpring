/**
 * Getting input from other classes
 */

package com.example.clothing;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class Input {
    
    @Autowired
    private Scanner CONSOLE;   

    public String getString() {
        System.out.println("Enter a string");
        return CONSOLE.nextLine();
    }
    
    public String getString(String str) {
        System.out.print(str);
        return CONSOLE.nextLine();
    }

    public String getRobustInput(String str) {
        String temp;
        while(true) {
            temp = getString(str);
            if(temp.length() > 0) {
                break;
            }
        }
        return temp; 
    }
}
