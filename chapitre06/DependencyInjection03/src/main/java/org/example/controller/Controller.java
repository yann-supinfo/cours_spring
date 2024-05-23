package org.example.controller;

import org.example.service.Service;

public class Controller {
    private Service service;

    public Controller(Service service) {
        this.service = service;
    }

    public void handleRequest() {
        String result = service.processData();
        System.out.println("Controller: " + result);
    }
}