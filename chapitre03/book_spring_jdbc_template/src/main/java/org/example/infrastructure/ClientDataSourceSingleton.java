package org.example.infrastructure;

import org.example.domain.Client;

import java.util.ArrayList;
import java.util.List;

public class ClientDataSourceSingleton {
    private static ClientDataSourceSingleton instance;
    public List<Client> clients;

    private ClientDataSourceSingleton () {
        this.clients = new ArrayList<>();
    }

    public static ClientDataSourceSingleton getInstance() {
        if (instance == null) {
            System.out.println("Create instance");
            instance = new ClientDataSourceSingleton();
        }
        System.out.println("get instance");
        return instance;
    }
}
