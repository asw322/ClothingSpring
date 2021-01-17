package com.example.clothing.controller;

import com.example.clothing.ProcessingInterface;
import com.example.clothing.AppConfig;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public abstract class ControllerAbstract {
    
    final ApplicationContext CONTEXT = new AnnotationConfigApplicationContext(AppConfig.class);
    public ProcessingInterface PROCESSING_INTERFACE = CONTEXT.getBean(ProcessingInterface.class); 
}
