package com.example.clothing;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccessControl {
    // @Autowired
    private Scanner CONSOLE;
    private int INVALID_LOGIN_ATTEMPTS;

    // @Autowired
    public AccessControl(Scanner _CONSOLE) {
        CONSOLE = _CONSOLE;
    }
}
