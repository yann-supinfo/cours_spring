package org.example.application.service;

import org.example.domain.Book;
import org.example.domain.BookFactoryManager;
import org.example.domain.Client;
import org.example.infrastructure.MysqlBookRepository;
import org.example.infrastructure.MysqlClientRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ClientService implements ServiceClientInterface {

    private MysqlClientRepository mysqlClientRepository;

    @Value("${service.name}")
    private String name;

    @Value("${service.type}")
    private String type;

    public ClientService(MysqlClientRepository mysqlClientRepository) {
        this.mysqlClientRepository = mysqlClientRepository;
    }

    @Override
    public List<Client> getClients() {
        System.out.println("Utilisation du service " + this.name + " de type " + this.type);
//System.out.println("Utilisation du service " + this.name + " de type " + this.type );
        return mysqlClientRepository.getClients();
    }

    @Override
    public void addClient(String firstname, String lastname) {
        System.out.println("Utilisation du service Client.");
        Client client = new Client(firstname, lastname);

        mysqlClientRepository.addClient(client);
    }

    @Override
    public void deleteClient(int index) {
        System.out.println("Utilisation du service Client.");
        mysqlClientRepository.deleteClientyIndex(index);
    }
}
