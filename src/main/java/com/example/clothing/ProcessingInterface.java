/**
 * Purpose of ProcessingInterface: 
 * Process the data that flows between the ClientInterface and the DAO classes. 
 * This class is also a part of the logical layer of the program. 
 * 
 * High level use cases: 
 * 1. Takes returned objects from DAO, cleans it (processing), and returns to ClientInterface
 * 2. Builds out the client request from ClientInterface
 */

package com.example.clothing;

import java.util.List;

import com.example.clothing.DAO.User_DAO.UserDataAccessService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProcessingInterface {

    @Autowired
    private UserDataAccessService USER_DATA_ACCESS_SERVICE;


    /**
     * Makes query call to retrieve user account information 
     * Handles error cases 
     * @param USERNAME
     * @param HASHEDPASSWORD
     * @return
     */
    public UserToken userLogin(String USERNAME, String HASHEDPASSWORD) {
        String sql = "SELECT * FROM person WHERE name = '" + USERNAME + "' AND hashedpassword = '" + HASHEDPASSWORD + "'";
        List<UserToken> res = USER_DATA_ACCESS_SERVICE.getUserData(sql);

        // Check to make sure list only contains 1 account 
        if(res.size() == 1) {
            return res.get(0);
        }
        else {
            return null;
        }
    }


    /**
     * Makes query call to insert new user 
     * Handles error case
     * returns the same USERTOKEN passed in 
     * @param USERTOKEN
     * @return
     */
    public UserToken createAccount(UserToken USERTOKEN) {
        String sql = "INSERT INTO person (id, name, hashedpassword) VALUES ('" + USERTOKEN.ID + "', '" + USERTOKEN.USERNAME + "', '" + USERTOKEN.HASHEDPASSWORD + "')";
        int rowsAffected = USER_DATA_ACCESS_SERVICE.insertNewUser(sql);

        if(rowsAffected == 1) {
            System.out.println("Insertion successful");
        }
        else {
            System.out.println("Something went wrong");
        }


        return USERTOKEN;
    }



    public String generateNewID() {
        String sql = "SELECT MAX(id) AS largestId FROM person";
        List<Integer> res = USER_DATA_ACCESS_SERVICE.getHighestID(sql);

        // Check if returned object is correct 

        // Highest user ID returned case
        if(res.size() == 1) {
            return "" + (res.get(0) + 1);
        }
        // No users in the person table case
        else if(res.size() == 0) {
            return "1";
        }
        // Error case
        else {
            System.out.println("Something went wrong");
            return "";
        }
    }
}
