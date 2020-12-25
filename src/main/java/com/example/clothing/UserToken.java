package com.example.clothing;

import org.springframework.stereotype.Component;

@Component
public class UserToken {
    public String USERNAME;
    public String HASHEDPASSWORD;
    public String NAME;
    public String BIRTHDATE;
    public String SEX;
    public String ID;

    /**
     * 
     * @param _USERNAME
     * @param _HASHEDPASSWORD
     * @param _NAME
     * @param _BIRTHDATE
     * @param _SEX
     * @param _ID
     */
    public void setAll(String _USERNAME, String _HASHEDPASSWORD, String _NAME, String _BIRTHDATE, String _SEX, String _ID) {
        USERNAME = _USERNAME;
        HASHEDPASSWORD = _HASHEDPASSWORD;
        NAME = _NAME;
        BIRTHDATE = _BIRTHDATE;
        SEX = _SEX;
        ID = _ID;
    }

    public void setLogin(String _USERNAME, String _HASHEDPASSWORD, String _ID) {
        USERNAME = _USERNAME;
        HASHEDPASSWORD = _HASHEDPASSWORD;
        ID = _ID;
    }

    public void printUserToken() {
        System.out.println("\nPrinting User Token: ");
        System.out.println("Username: " + USERNAME);
        System.out.println("Hased Password: " + HASHEDPASSWORD);
        System.out.println("ID: " + ID);
    }

}
