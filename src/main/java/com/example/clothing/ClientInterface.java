package com.example.clothing;

import java.util.Collections;
import java.util.List;

// import com.example.clothing.DAO.User_DAO.UserDataAccessService;
// import com.example.clothing.DAO.Clothing_DAO.ClothingDataAccessService; 
// import com.example.clothing.DAO.Personality_DAO.PersonalityDataAccessService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClientInterface {
    
    @Autowired
    private AccessControl CONTROL;

    @Autowired
    private Encrypt ENCRYPT;

    @Autowired
    private Input INPUT;

    @Autowired
    private UserToken USERTOKEN;

    // @Autowired 
    // private UserDataAccessService USER_DATA_ACCESS_SERVICE;

    @Autowired
    private ProcessingInterface PROCESSING_INTERFACE;

    public ClientInterface() {
        //Empty
    }  

    /**
     * Login through AccessControl
     */
    public void login() {
        USERTOKEN = CONTROL.login();
    }

    /**
     * Create a new account through AccessControl
     */
    public void createAccount() {
        USERTOKEN = CONTROL.createAccount();
    }

    /**
     * Show the user's portfolio
     */
    public void showPortfolio() {
        printHeader("My Portfolio");

        String action;

        // Get user input
        while(true) {
            action = INPUT.getRobustInput("Select your action:\n1. See Outfit Collection\n2. Recommend An Outfit\n3. Settings\n4. Exit\nEnter: ");
            if(action.equals("1") || action.equals("2") || action.equals("3") || action.equals("4")) {
                break;
            }
        }

        int intAction = Integer.parseInt(action);

        // See outfit collection
        if(intAction == 1) {
            seeCollection();
        }
        // Recommend an outfit
        else if(intAction == 2) {
            recOutfit();
        }
        // Settings
        else if(intAction == 3) {
            settings();
        }
        else if(intAction == 4) {
            System.out.println("Thanks for using Clothing Spring..Bye");
            System.exit(2);
        }
    }

    public List<ProductToken> seeCollection() {
        printHeader("Showing Collection");

        List<ProductToken> res = PROCESSING_INTERFACE.getWholeCollection(USERTOKEN.ID);
        int res_size = res.size();

        for(int i = 0; i < res_size; i++) {
            res.get(i).printProductToken();
        }

        return res; 
    }

    public List<ProductToken> queryUserProduct() {
        printHeader("Querying for Specific Product");

        List<ProductToken> res = PROCESSING_INTERFACE.getUserProduct(USERTOKEN.ID);
        int res_size = res.size();

        for(int i = 0; i < res_size; i++) {
            res.get(i).printProductToken();
        }

        return res;
    }

    // TODO: makes call to ClothingDataAccessService
    public List<ProductToken> queryAnyProduct() {
        return Collections.emptyList();
    }

    /**
     * Future implementations: 
     * Show the most worn product for each major categories 
     * or allow user to input the category they want to see
     */
    public List<Object[]> seeMostWorn() {
        printHeader("Showing Most Worn Product");

        // There may be multiple most worn products
        List<Object[]> res = PROCESSING_INTERFACE.getMostWornProduct(USERTOKEN.ID);
        int res_size = res.size();

        for(int i = 0; i < res_size; i++) {
            ProductToken product = (ProductToken) res.get(i)[0];
            System.out.println("Wear count: " + res.get(i)[1]);
            product.printProductToken();
        }

        return res; 
    }

    private void recOutfit() {
        printHeader("Recommending Outfit");
    }

    private void settings() {
        printHeader("Settings");

        String action;

        while(true) {
            action = INPUT.getRobustInput("Select your action:\n1. Change Username\n2. Change Password\n3. Change Sex\nAny: Return\nEnter: ");

            if(action.equals("1")) {
                changeUsername();
            }
            else if(action.equals("2")) {
                changePassword();
            }
            else if(action.equals("3")) {
                changeSex();
            }
            else {
                break;
            }
        }
    }


    /**
     * SETTINGS HELPER FUNCTIONS
     */

    private void changeUsername() {
        printHeader("Change Username");

        String original, modified;

        // Enter old username 
        original = INPUT.getRobustInput("Enter Old Username: ");

        // Enter new username
        modified = INPUT.getRobustInput("Enter New Username: ");

        // Modify username 
            // First check if original username matches the username on UserToken
        
        if(original.equalsIgnoreCase(USERTOKEN.USERNAME)) {

            // SQL UPDATE statement
            String sql = "";


        } else {
            System.out.println("Old Username did not match up");
        }
    }

    private void changePassword() {
        printHeader("Change Password");

        String original, modified;

        // Enter old password 
        original = INPUT.getRobustInput("Enter Old Password: ");

        while(true) {
            // Enter new password
            modified = INPUT.getRobustInput("Enter New Password: ");

            String PASSWORD2 = INPUT.getString("Re-enter New Password: ");
            if(modified.equals(PASSWORD2)) {
                break;
            }
            else {
                System.out.println("New passwords do not match");
            }
        }

        // Hash the password
        String original_hashed = ENCRYPT.getHash(original);
        original = "";

        
        if(original_hashed.equalsIgnoreCase(USERTOKEN.HASHEDPASSWORD)) {

            // SQL UPDATE statement
            String sql = "";


        } else {
            System.out.println("Old password did not match up");
        }
    }

    private void changeSex() {
        printHeader("Change Sex");

        String modified;

        modified = INPUT.getRobustInput("Enter New Sex");

        String sql = "";

        // UPDATE the sex parameter
    }




    /**
     * HELPER FUNCTIONS TO PRINT 
     */

    
    /**
    * Prints headers
    * @param headerName
    */
    public void printHeader(String headerName) {
        System.out.println("\n");
        System.out.println(headerName);
        System.out.println("--------------------------");
    }
}
