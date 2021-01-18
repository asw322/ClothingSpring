/**
 * https://www.youtube.com/watch?v=35EQXmHKZYs&t=3360s
 * 1:44:00
 * 
 */

package com.example.clothing.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.clothing.UserToken;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

// Controller for home.jsp
@Controller
public class HomeController extends ControllerAbstract {

    private int INVALID_LOGIN_ATTEMPTS = 0;

    @RequestMapping("/home")
    // @ResponseBody
    // HttpServletRequest is a request disbatcher
    // HttpSession is automatically injected based on dependency injection
    public ModelAndView home() {
        // UserToken user = new UserToken();
        // user.setLogin(name, password, id);
        ModelAndView mv = new ModelAndView();

        // mv.addObject("username", user.USERNAME);
        // mv.addObject("hashedpassword", user.HASHEDPASSWORD);
        // mv.addObject("id", user.ID);

        // mv.addObject("usertoken", user);
        mv.setViewName("home");     // name of the jsp file 
        return mv;
    }

    @RequestMapping("/home/addUser")
    public ModelAndView addUser(@RequestParam("name") String name, @RequestParam("password") String password) {
        UserToken user = new UserToken();
        user.setLogin(name, password, super.PROCESSING_INTERFACE.generateNewID());
        System.out.println(user.USERNAME);
        System.out.println(user.HASHEDPASSWORD);

        // Creates a user 
        super.PROCESSING_INTERFACE.createAccount(user);

        ModelAndView mv = new ModelAndView("home");
        return mv;
    }

    // @RequestMapping("/login")
    // public ModelAndView login(@RequestParam("name") String name, @RequestParam("password") String password) {
    //     UserToken user = super.ACCESS_CONTROL.login(name, password);
    //     ModelAndView mv = new ModelAndView("successfulLogin");

    //     if(user == null) {
    //         // Prompt user to login again 
    //         this.INVALID_LOGIN_ATTEMPTS++;

    //         if(this.INVALID_LOGIN_ATTEMPTS == 4) {
    //             // Lock the user account
    //         }
    //     }
    //     else {
    //         // Login successful
    //         mv.addObject("user",user);
    //     }
        
    //     return mv;
    // }
}

