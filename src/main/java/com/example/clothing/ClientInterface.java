package com.example.clothing;

// import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClientInterface {
    
    // @Autowired
    public ClientInterface() {
        System.out.println("Client Interface Instance Created");
    }
    public void test() {
        System.out.println("this is a test");
    }   

}
