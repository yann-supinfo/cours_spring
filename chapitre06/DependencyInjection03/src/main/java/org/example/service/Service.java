package org.example.service;

import org.example.repository.Repository;

public class Service {
    private Repository repository;

    public Service(Repository repository) {
        this.repository = repository;
    }

    public String processData() {
        return repository.getData() + " processed by service";
    }
}