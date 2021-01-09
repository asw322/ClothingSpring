package com.example.clothing.NetworkRequest;

import java.io.FileWriter;
import java.io.IOException;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

import java.io.FileReader;

// import com.example.clothing.NetworkRequest.org.json.simple.*;
// import com.example.clothing.DAO.Clothing_DAO;
// import com.example.clothing.DAO.User_DAO.UserDataAccessService;



public class Request_HM {

    private static final String path = "/Users/alan/Desktop/Project/ClothingSpring/src/main/resources/data/HM/HM_data.json";
    
    private static void fetchData(FileWriter fw) {
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

    private static void parseData() {
        try {
            Object obj = new JSONParser().parse(new FileReader(path));
            JSONObject jo = (JSONObject) obj;
            JSONArray res = (JSONArray) jo.get("results");
            
            JSONObject jo2 = (JSONObject) res.get(0);

            System.out.println((String) jo.get("starter"));
            System.out.println((String) jo2.get("code"));
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        FileWriter fw = null; 

        // fetchData(fw);      // Sets up all the data (Warning: will rewrite HM_data.json)
        parseData();        // Parses all the data
    }
}