package org.example.application.controller;

import org.example.application.service.BookService;
import org.example.application.service.ClientService;
import org.example.domain.Book;
import org.example.domain.Client;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ClientController {

    private ClientService clientService;


    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    public List<Client> getClients() {
        return clientService.getClients();
    }

    public void addClient(String firstname, String lastname) {
        this.clientService.addClient(firstname, lastname);
    }

    public void deleteClient(int index) {
        this.clientService.deleteClient(index);
    }
}
