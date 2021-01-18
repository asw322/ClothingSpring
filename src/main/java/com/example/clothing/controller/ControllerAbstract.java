package com.example.clothing.controller;

import com.example.clothing.ProcessingInterface;
import com.example.clothing.AccessControl;
import com.example.clothing.AppConfig;
import com.example.clothing.ClientInterface;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public abstract class ControllerAbstract {
    
    final ApplicationContext CONTEXT = new AnnotationConfigApplicationContext(AppConfig.class);
    public ProcessingInterface PROCESSING_INTERFACE = CONTEXT.getBean(ProcessingInterface.class); 
    public AccessControl ACCESS_CONTROL = CONTEXT.getBean(AccessControl.class);
    public ClientInterface CLIENT_INTERFACE = CONTEXT.getBean(ClientInterface.class);
}
