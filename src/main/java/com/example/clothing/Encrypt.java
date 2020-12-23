package com.example.clothing;

import org.springframework.stereotype.Component;

import java.math.BigInteger;  
import java.nio.charset.StandardCharsets; 
import java.security.MessageDigest;  
import java.security.NoSuchAlgorithmException; 

@Component
public class Encrypt {
    public String getHash(String _password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(_password.getBytes(StandardCharsets.UTF_8));
            BigInteger number = new BigInteger(1, hash);
            StringBuilder hexString = new StringBuilder(number.toString(16));

            while(hexString.length() < 32) {
                hexString.insert(0, '0');
            }

            return hexString.toString();
        } catch(NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "null";
    }
}
