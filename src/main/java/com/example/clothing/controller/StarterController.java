package com.example.clothing.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StarterController extends ControllerAbstract {
    
    @RequestMapping("/")
    public ModelAndView home() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("starter");
        return mv;
    }

    @RequestMapping("/starter") 
    public ModelAndView login(@RequestParam("input") String input) {
        ModelAndView mv = new ModelAndView();

        if(input.equals("Login")) {
            mv.addObject("initial_text", "Logging you in");
            System.out.println("want log in");
        }
        else if(input.equals("SignUp")) {
            mv.addObject("initial_text", "Signing you up");
            System.out.println("want sign up");
        }
        

        return mv;
    }

}
