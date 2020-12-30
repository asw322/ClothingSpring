package com.example.clothing.NetworkRequest;

import java.io.IOException;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

public class Request_HM {
    public static void main(String[] args) {
        try {
            OkHttpClient client = new OkHttpClient();
        
            Request request = new Request.Builder()
                .url("https://apidojo-hm-hennes-mauritz-v1.p.rapidapi.com/products/list?country=asia2&lang=en&currentpage=0&pagesize=30&categories=men_all&concepts=H%26M%20MAN")
                .get()
                .addHeader("x-rapidapi-key", "01a77106c5msh284168c2a29ac03p11f654jsnc4ffd811ec54")
                .addHeader("x-rapidapi-host", "apidojo-hm-hennes-mauritz-v1.p.rapidapi.com")
                .build();
            
            Response response = client.newCall(request).execute();
            System.out.println("Printing now: ");
            System.out.println(response.body().string());
        }   
        catch(IOException e) {
            e.printStackTrace();
        }        
    }
}