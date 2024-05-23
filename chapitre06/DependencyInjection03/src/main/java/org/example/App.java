package org.example;

import org.example.controller.Controller;
import org.example.repository.Repository;
import org.example.service.Service;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Repository repository = new Repository();
        Service service = new Service(repository);
        Controller controller = new Controller(service);

        controller.handleRequest();
    }
}
