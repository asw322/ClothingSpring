package com.example.clothing.controller;

import com.example.clothing.UserToken;
import com.example.clothing.UnprocessedUserToken;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class UserController extends ControllerAbstract {

    // @GetMapping("/api/user") 
    // public UserToken login(UnprocessedUserToken U_USERTOKEN) {
    //     System.out.println("username = " + U_USERTOKEN.USERNAME);
    //     System.out.println("password = " + U_USERTOKEN.PASSWORD);
    //     UserToken res = super.ACCESS_CONTROL.login(U_USERTOKEN.USERNAME, U_USERTOKEN.PASSWORD);
    //     return res;
    // }

    @GetMapping("/api/user") 
    public UserToken loginParameter(@RequestParam("username") String username, @RequestParam("password") String password) {
        System.out.println("username = " + username);
        System.out.println("password = " + password);
        UserToken res = super.ACCESS_CONTROL.login(username, password);
        return res;
    }
}
