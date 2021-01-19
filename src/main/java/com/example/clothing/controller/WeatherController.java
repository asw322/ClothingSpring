package com.example.clothing.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/weather")
public class WeatherController {
    
    @GetMapping("/")
    public String forecast() {
        return "Maybe sun .. Maybe rain";
    }
}
