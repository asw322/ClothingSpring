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

import com.example.clothing.DAO.Clothing_DAO.ClothingDataAccessService;
import com.example.clothing.DAO.Personality_DAO.PersonalityDataAccessService;
import com.example.clothing.DAO.User_DAO.UserDataAccessService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.apache.commons.validator.routines.UrlValidator;

@Component
public class ProcessingInterface {

    @Autowired
    private UserDataAccessService USER_DATA_ACCESS_SERVICE;

    @Autowired
    private ClothingDataAccessService CLOTHING_DATA_ACCESS_SERVICE;

    @Autowired
    private PersonalityDataAccessService PERSONALITY_DATA_ACCESS_SERVICE;

    @Autowired
    private Input INPUT;

    private UrlValidator URL_VALIDATOR;

    public ProcessingInterface() {
        // Setting up URL validator
        String[] schemes = {"http", "https"};
        URL_VALIDATOR = new UrlValidator(schemes);
    }


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










    public List<ProductToken> getWholeCollection(String ID) {
        // Future impleemtnation idea: call ClothingDataAccessService instead of making it one line
        String sql = "SELECT * FROM product WHERE product_id = (SELECT product_id FROM closet WHERE id = '" + ID + "')";
        List<ProductToken> res = USER_DATA_ACCESS_SERVICE.getUserOwnedProductDetail(sql);
        
        if(res == null) {
            System.out.println("Something went wrong");
        }
        return res;
    }

    public List<ProductToken> getUserProduct(String ID) {
        // Get the product name first 
        String in = INPUT.getRobustInput("Please Enter Product Name or URL: ");
        List<ProductToken> res = null;

        // Is a URL
        if(URL_VALIDATOR.isValid(in)) {
            System.out.println("Making Query on Product URL");
            // MAKE QUERY TO USERDATAACCESSSERVICE.GETUSEROWNEDPRODUCTDETAIL BUT USE QUERY PARAMETER ON PRODUCT_URL
        }
        // Is NOT a URL
        else {
            System.out.println("Making Query on Product Name");
            // MAKE QUERY TO USERDATAACCESSSERVICE.GETUSEROWNEDPRODUCTDETAIL BUT USE QUERY PARAMETER ON PRODUCT_NAME
        }

        // Clean up the result stored in res

        return res;
    }


    /**
     * return a list of objects because 
     * @param ID
     * @return
     */
    public List<Object[]> getMostWornProduct(String ID) {
        String sql = "SELECT *FROM user_data INNER JOIN closet ON user_data.id = closet.id WHERE user_data.id = '" + ID + "' ORDER BY wear_count DESC LIMIT 1";
        
        List<Object[]> res = USER_DATA_ACCESS_SERVICE.getUserOwnedProductDetailWithWearCount(sql);

        // Do we need to process res? 

        return res;
    }
}
