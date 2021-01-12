package com.example.clothing;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.stereotype.Component;

@Component
public class ProductToken {
    public String PRODUCT_ID;
    public String MANUFACTURER_NAME;
    public String PRODUCT_REFERENCE_NUMBER;
    public String PRODUCT_NAME;
    public String PRODUCT_DESCRIPTION;
    public double PRICE_IN_DOLLARS;
    public String PRODUCT_LENGTH;
    public String PRODUCT_HEIGHT;
    public String PRODUCT_WIDTH;
    public String PRODUCT_STYLE;
    public String PRODUCT_COLOR;
    public String PRODUCT_URL;

    public ArrayList<String> PICTURE_URL_ARR;

    public ProductToken() {

    }
    
    public ProductToken(
        String _PRODUCT_ID, 
        String _MANUFACTURER_NAME, 
        String _PRODUCT_REFERENCE_NUMBER, 
        String _PRODUCT_NAME, 
        String _PRODUCT_DESCRIPTION, 
        double _PRICE_IN_DOLLARS, 
        String _PRODUCT_LENGTH, 
        String _PRODUCT_HEIGHT, 
        String _PRODUCT_WIDTH, 
        String _PRODUCT_STYLE, 
        String _PRODUCT_COLOR, 
        String _PRODUCT_URL,
        ArrayList<String> _PICTURE_URL_ARR) {
            PRODUCT_ID = _PRODUCT_ID;
            MANUFACTURER_NAME = _MANUFACTURER_NAME;
            PRODUCT_REFERENCE_NUMBER = _PRODUCT_REFERENCE_NUMBER;
            PRODUCT_NAME = _PRODUCT_NAME;
            PRODUCT_DESCRIPTION = _PRODUCT_DESCRIPTION;
            PRICE_IN_DOLLARS = _PRICE_IN_DOLLARS;
            PRODUCT_LENGTH = _PRODUCT_LENGTH;
            PRODUCT_HEIGHT = _PRODUCT_HEIGHT;
            PRODUCT_WIDTH = _PRODUCT_WIDTH;
            PRODUCT_STYLE = _PRODUCT_STYLE;
            PRODUCT_COLOR = _PRODUCT_COLOR;
            PRODUCT_URL = _PRODUCT_URL;
            PICTURE_URL_ARR = _PICTURE_URL_ARR;
    }

    public void setAll(
        String _PRODUCT_ID, 
        String _MANUFACTURER_NAME, 
        String _PRODUCT_REFERENCE_NUMBER, 
        String _PRODUCT_NAME, 
        String _PRODUCT_DESCRIPTION, 
        double _PRICE_IN_DOLLARS, 
        String _PRODUCT_LENGTH, 
        String _PRODUCT_HEIGHT, 
        String _PRODUCT_WIDTH, 
        String _PRODUCT_STYLE, 
        String _PRODUCT_COLOR, 
        String _PRODUCT_URL,
        ArrayList<String> _PICTURE_URL_ARR) {
            PRODUCT_ID = _PRODUCT_ID;
            MANUFACTURER_NAME = _MANUFACTURER_NAME;
            PRODUCT_REFERENCE_NUMBER = _PRODUCT_REFERENCE_NUMBER;
            PRODUCT_NAME = _PRODUCT_NAME;
            PRODUCT_DESCRIPTION = _PRODUCT_DESCRIPTION;
            PRICE_IN_DOLLARS = _PRICE_IN_DOLLARS;
            PRODUCT_LENGTH = _PRODUCT_LENGTH;
            PRODUCT_HEIGHT = _PRODUCT_HEIGHT;
            PRODUCT_WIDTH = _PRODUCT_WIDTH;
            PRODUCT_STYLE = _PRODUCT_STYLE;
            PRODUCT_COLOR = _PRODUCT_COLOR;
            PRODUCT_URL = _PRODUCT_URL;
            PICTURE_URL_ARR = _PICTURE_URL_ARR;
    }

    public void setDimensions(String _PRODUCT_LENGTH, String _PRODUCT_HEIGHT, String _PRODUCT_WIDTH) {
        PRODUCT_LENGTH = _PRODUCT_LENGTH;
        PRODUCT_HEIGHT = _PRODUCT_HEIGHT;
        PRODUCT_WIDTH = _PRODUCT_WIDTH;
    }

    public void printProductToken() {
        System.out.println("\nPrinting Product Token: ");
        System.out.println("Product ID: " + PRODUCT_ID);
        System.out.println("Manufacturer's Name: " + MANUFACTURER_NAME);
        System.out.println("Reference Number: " + PRODUCT_REFERENCE_NUMBER);
        System.out.println("Name: " + PRODUCT_NAME);
        System.out.println("Description: " + PRODUCT_DESCRIPTION);
        System.out.println("Price (USD): $" + PRICE_IN_DOLLARS);
        System.out.println("Length: " + PRODUCT_LENGTH);
        System.out.println("Height: " + PRODUCT_HEIGHT);
        System.out.println("Width: " + PRODUCT_WIDTH);
        System.out.println("Style: " + PRODUCT_STYLE);
        System.out.println("Color: " + PRODUCT_COLOR);
        System.out.println("URL: " + PRODUCT_URL);
        System.out.println("PICTURE URL: " + PICTURE_URL_ARR);
    }
}
