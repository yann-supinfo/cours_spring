package org.example.infrastructure;

import org.example.domain.Book;
import org.example.domain.Client;

import java.util.List;

public interface ClientRepositoryInterface {
    public List<Client> getClients();
    public void addClient(Client client);
    public void deleteClientByIndex(int index);

}
