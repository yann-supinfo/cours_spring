package org.example;

import javax.inject.Singleton;

public class ConnectionBDD {

    private static ConnectionBDD instance;
    public String name;

    private ConnectionBDD (String name) {
        this.name = name;
    }

    public static ConnectionBDD getInstance(String name) {
        if (instance == null) {
            System.out.println("Create instance");
            instance = new ConnectionBDD(name);
        }
        System.out.println("get instance");
        return instance;
    }


}
