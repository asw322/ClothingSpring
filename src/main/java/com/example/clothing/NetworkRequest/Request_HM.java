package com.example.clothing.NetworkRequest;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.example.clothing.ProcessingInterface;
import com.example.clothing.ProductToken;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileReader;

// import com.example.clothing.NetworkRequest.org.json.simple.*;
// import com.example.clothing.DAO.Clothing_DAO;
// import com.example.clothing.DAO.User_DAO.UserDataAccessService;


@Component
public class Request_HM {

    private static final String path = "/Users/alan/Desktop/Project/ClothingSpring/src/main/resources/data/HM/HM_data.json";

    @Autowired
    private ProcessingInterface PROCESSING_INTERFACE = new ProcessingInterface();
    
    public void fetchData(FileWriter fw) {
        // Outputting Response to json object
        try {
            fw = new FileWriter(path);
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                .url("https://apidojo-hm-hennes-mauritz-v1.p.rapidapi.com/products/list?country=asia2&lang=en&currentpage=0&pagesize=30&categories=men_all&concepts=H%26M%20MAN")
                .get()
                .addHeader("x-rapidapi-key", "01a77106c5msh284168c2a29ac03p11f654jsnc4ffd811ec54")
                .addHeader("x-rapidapi-host", "apidojo-hm-hennes-mauritz-v1.p.rapidapi.com")
                .build();
            
            Response response = client.newCall(request).execute();
            System.out.println("Printing now: ");
            // System.out.println(response.body().string());

            String res = response.body().string();

            fw.write(res);

            fw.close();
        }   
        catch(IOException e) {
            e.printStackTrace();
        }    
        // Ensure fw is closed
        finally {
            if(fw != null) {
                try {
                    fw.close();
                }
                catch(IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void tester() {
        PROCESSING_INTERFACE.userLogin("test", "test");
    }

    public void parseData() {
        ArrayList<ProductToken> product_arr = new ArrayList<>();

        try {
            Object obj = new JSONParser().parse(new FileReader(path));
            JSONObject jo = (JSONObject) obj;
            JSONArray res = (JSONArray) jo.get("results");

            for(int i = 0; i < res.size(); i++) {
                JSONObject tempClothing = (JSONObject) res.get(i);


                String PRODUCT_ID = PROCESSING_INTERFACE.getHighestProductID();      // Need to query for the highest PID
                String MANUFACTURER_NAME = "HM";
                String PRODUCT_REFERENCE_NUMBER = "HM_" + (String) tempClothing.get("code");
                String PRODUCT_NAME = (String) tempClothing.get("name");
                String PRODUCT_DESCRIPTION = null;
                double PRICE_IN_DOLLARS = (Double) ((JSONObject) tempClothing.get("price")).get("value");
                String PRODUCT_LENGTH = null;
                String PRODUCT_HEIGHT = null;
                String PRODUCT_WIDTH = null;
                String PRODUCT_STYLE = null;       // Might be able to get this from JSON?

                String PRODUCT_COLOR = "sample color";

                // String PRODUCT_COLOR = (String) ((JSONObject) tempClothing.get("color")).get("text");
                String PRODUCT_URL = null;         // Might be able to get this from JSON?

                /**
                 * get the [images, articles->images, logoPicture, normalPicture] 
                 * Might have to check if all other products follow the same format?
                 * Solution: try to print out the URLS for every product to terminal first? 
                 */
                ArrayList<String> PICTURE_URL_ARR = new ArrayList<>();

                JSONArray imageArr = (JSONArray) tempClothing.get("images");
                JSONObject imageObj = (JSONObject) imageArr.get(0);
                String imageUrl = (String) imageObj.get("url");
                PICTURE_URL_ARR.add(imageUrl);


                JSONArray logoPicture = (JSONArray) tempClothing.get("articles");
                JSONObject logoPictureObj = (JSONObject) logoPicture.get(0);
                String logoPictureUrl = (String) logoPictureObj.get("url");
                PICTURE_URL_ARR.add(logoPictureUrl);

                
                ProductToken product = new ProductToken(PRODUCT_ID, MANUFACTURER_NAME, PRODUCT_REFERENCE_NUMBER, PRODUCT_NAME, PRODUCT_DESCRIPTION, PRICE_IN_DOLLARS, PRODUCT_LENGTH, PRODUCT_HEIGHT, PRODUCT_WIDTH, PRODUCT_STYLE, PRODUCT_COLOR, PRODUCT_URL, PICTURE_URL_ARR);
                product_arr.add(product);
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        // Printing for testing
        for(int i = 0; i < product_arr.size(); i++) {
            product_arr.get(i).printProductToken();
        }

        // Add product_arr product tokens to database
        // PROCESSING_INTERFACE.insertProductToken(product_arr);
    }

    public static void main(String[] args) {
        FileWriter fw = null; 

        // PROCESSING_INTERFACE = new ProcessingInterface();

        // fetchData(fw);      // Sets up all the data (Warning: will rewrite HM_data.json)
        // parseData();        // Parses all the data
    }
}