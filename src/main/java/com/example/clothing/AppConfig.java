/**
 * NOTE: 
 * Configuration files create instances of singleton beans defined in this class
 * and does not recreate an new instance whenever a ContextApplication.getBean() call is made
 */

package com.example.clothing;

import java.util.*;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public Scanner getScannerIn() {
        return new Scanner(System.in);
    }

    @Bean
    public Input getInput() {
        return new Input();
    }

    @Bean
    public ClientInterface getClientInterface() {
        return new ClientInterface(); 
    }

    @Bean
    public AccessControl getAccessControl() {
        return new AccessControl();
    }

    @Bean
    public Encrypt getEncrypt() {
        return new Encrypt();
    }

    @Bean
    public UserToken getUserToken() {
        return new UserToken();
    }
}
