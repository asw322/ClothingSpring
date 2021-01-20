package com.example.clothing.controller;

import com.example.clothing.UserToken;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class UserController {
    @Autowired
    private UserToken USERTOKEN;

    @GetMapping("/api/user") 
    public UserToken login() {
        System.out.println("We're on the backend");
        UserToken temp = new UserToken();
        temp.ID = 100;

        return temp;
    }
}
