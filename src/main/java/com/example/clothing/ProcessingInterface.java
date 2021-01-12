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

import java.util.ArrayList;
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


    // DEFINED CONSTANTS
    private String STANDARD_ERROR = "Something went wrong";

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
            System.out.println(STANDARD_ERROR);
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
            System.out.println(STANDARD_ERROR);
            return "";
        }
    }










    public List<ProductToken> getWholeCollection(String ID) {
        // Future impleemtnation idea: call ClothingDataAccessService instead of making it one line
        String sql = "SELECT * FROM product WHERE product_id = (SELECT product_id FROM closet WHERE id = '" + ID + "')";
        List<ProductToken> res = USER_DATA_ACCESS_SERVICE.getUserOwnedProductDetail(sql);
        
        if(res == null) {
            System.out.println(STANDARD_ERROR);
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
        String sql = "SELECT *FROM user_data NATURAL JOIN closet WHERE id = '" + ID + "' ORDER BY wear_count DESC LIMIT 1";
        
        List<Object[]> res = USER_DATA_ACCESS_SERVICE.getUserOwnedProductDetailWithWearCount(sql);

        // Do we need to process res? 

        return res;
    }


    public List<PersonalityToken> getAllPersonality() {
        String sql = "SELECT * FROM personality";
        return PERSONALITY_DATA_ACCESS_SERVICE.getAllPersonality(sql);
    }


    public PersonalityToken getPersonality() {
        String in = INPUT.getRobustInput("Enter Personality Type: ");
        String sql = "SELECT * FROM personality WHERE personality_types = '" + in + "'";

        List<PersonalityToken> res = PERSONALITY_DATA_ACCESS_SERVICE.getPersonality(sql);

        if(res.size() == 1) {
            return res.get(0);
        }
        else if(res.size() == 0) {
            System.out.println("The personality type does not exist");
            return new PersonalityToken();
        }
        else {
            return null;
        }
    }

    
    public List<PersonalityToken> getMostPopularPersonality() {
        String sql = "SELECT * FROM personality ORDER BY personality_count DESC LIMIT 1";
        return PERSONALITY_DATA_ACCESS_SERVICE.getMostPopularPersonality(sql);
    }

    public List<PersonalityToken> getPersonalityStrength(String ID) {
        String sql = "SELECT * FROM user_personality NATURAL JOIN personality WHERE id = '" + ID + "' ORDER BY personality_strength DESC";
        List<PersonalityToken> res =  PERSONALITY_DATA_ACCESS_SERVICE.getPersonalityStrength(sql);

        return res;
    }


    public String getHighestProductID() {
        String sql = "SELECT MAX(product_id) AS largestPID FROM product";
        List<String> res = CLOTHING_DATA_ACCESS_SERVICE.getHighestPID(sql);

        int curr_highest = Integer.parseInt(res.get(0).substring(1));
        return "P" + (curr_highest + 1);
    }




    public int insertProductToken(ArrayList<ProductToken> product_arr) {
        int product_arr_size = product_arr.size();
        if(product_arr_size == 0) {
            System.out.println("No products found");
            return 0;
        }

        int product_insertion_count = 0;

        // Inserting into product
        for(int i = 0; i < product_arr_size; i++) {
            ProductToken temp = product_arr.get(i);
            String sql = "INSERT INTO product(product_id, manufacturer_name, product_reference_number, product_name, product_description, price_in_dollars, product_length, product_height, product_width, product_style, product_color, product_url) VALUES ('" + temp.PRODUCT_ID + "', '" + temp.MANUFACTURER_NAME + "', '" + temp.PRODUCT_REFERENCE_NUMBER + "', '" + temp.PRODUCT_NAME + "', '" + temp.PRODUCT_DESCRIPTION + "', " + temp.PRICE_IN_DOLLARS + ", '" + temp.PRODUCT_LENGTH + "', '" + temp.PRODUCT_HEIGHT + "', '" + temp.PRODUCT_WIDTH + "', '" + temp.PRODUCT_STYLE + "', '" + temp.PRODUCT_COLOR + "', '" + temp.PRODUCT_URL + "')";
            product_insertion_count += CLOTHING_DATA_ACCESS_SERVICE.executeUpdate(sql);


            // Inserting into product image
            int product_image_insertion_count = 0;
            int picture_url_arr_size = temp.PICTURE_URL_ARR.size();
            for(int j = 0; j < picture_url_arr_size; j++) {
                String sql2 = "INSERT INTO product_image(product_id, product_image) VALUES ('" + temp.PRODUCT_ID + "', '" + temp.PICTURE_URL_ARR.get(j) + "')";
                product_image_insertion_count += CLOTHING_DATA_ACCESS_SERVICE.executeUpdate(sql2);
            }

            if(product_image_insertion_count == picture_url_arr_size) {
                System.out.println("Images insertion completed");
            }
        }        

        return product_insertion_count;
    }
}
