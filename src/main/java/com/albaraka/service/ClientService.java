package com.albaraka.service;

import com.albaraka.dao.ClientDAO;
import com.albaraka.entity.Client;

import java.util.List;
import java.util.Optional;

public class ClientService {
    private final ClientDAO clientDAO;

    public ClientService() {
        this.clientDAO = new ClientDAO();
    }

    public void createClient(Client client) {
        clientDAO.save(client);
    }

    public Optional<Client> getClientById(int id) {
        return clientDAO.findById(id);
    }

    public List<Client> getAllClients() {
        return clientDAO.findAll();
    }

    public void updateClient(Client client) {
        clientDAO.update(client);
    }

    public void deleteClient(int id) {
        clientDAO.delete(id);
    }
}
