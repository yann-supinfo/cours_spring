package org.example.infrastructure;

import org.example.domain.Client;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MysqlClientRepository {
    public ClientDataSourceSingleton clientDataSourceSingleton;

    public MysqlClientRepository() {

        this.clientDataSourceSingleton = ClientDataSourceSingleton.getInstance();
        System.out.println("Mysql Repository Created");
    }

    public List<Client> getClients() {
        return this.clientDataSourceSingleton.clients;
    }

    public void addClient(Client client) {
        this.clientDataSourceSingleton.clients.add(client);
        System.out.println("Le client " + client.getFirstname() + " a été ajouté au repository mysql avec succès");
    }

    public void deleteClientyIndex(int index) {
        for (int i = 0; i < clientDataSourceSingleton.clients.size(); i++) {


            if (i == index) {
                String name = clientDataSourceSingleton.clients.get(i).getFirstname();
                clientDataSourceSingleton.clients.remove(i);

                System.out.println("Le client " + name + " a été supprimer de la base mysql avec succès");

            }
        }

    }
}
