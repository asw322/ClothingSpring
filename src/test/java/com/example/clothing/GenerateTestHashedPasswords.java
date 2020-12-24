package com.example.clothing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.io.File;

import java.util.List;
import java.util.ArrayList;


import java.math.BigInteger;  
import java.nio.charset.StandardCharsets; 
import java.security.MessageDigest;  
import java.security.NoSuchAlgorithmException; 


class GenerateTestHashedPassword {
    private static BufferedReader br; 
    private static FileWriter wr; 
    private static final String filename_input = "/Users/alan/Desktop/Project/ClothingSpring/src/test/java/com/example/clothing/PasswordHashes/Plaintextpassword.txt";
    private static final String filename_output = "/Users/alan/Desktop/Project/ClothingSpring/src/test/java/com/example/clothing/PasswordHashes/Ciphertextpassword.txt";


    public static String getHash(String _password) {
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

    public static void main(String[] args) {

        // Read in the Plaintextpassword file 
        // Hash each password using SHA-256 hash function
        // Output hashed password to Ciphertextpassword file

        List<String> hashedList = new ArrayList<>();

        try {
            br = new BufferedReader(new FileReader(filename_input));
            wr = new FileWriter(new File(filename_output), true);

            String contentLine = br.readLine();
            while (contentLine != null) {
                System.out.println(contentLine);

                hashedList.add(getHash(contentLine));

                contentLine = br.readLine();
            }

            int listSize = hashedList.size();
            for(int i = 0; i < listSize; i++) {
                wr.write(hashedList.get(i) + "\n");
            }

            br.close();
            wr.close();
        } 
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    
}
