package org.example.application.service;

import org.example.domain.Book;
import org.example.domain.Client;

import java.util.List;

public interface ServiceClientInterface {
    List<Client> getClients();
    public void addClient(String firstname, String lastname);
    public void deleteClient(int index);
}
