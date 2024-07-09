package com.gas.port;

import com.gas.dominio.Client;

import java.util.List;

public interface ClientRepository {
    List<Client> getAllClients();
    Client getClientById(String id);
    Client getClientByBusinessId(String id);
    Client getClientByField(String fieldName,String fieldValue);
    Client addClient(Client client);
    void updateClient(Client client);
    void deleteClient(Long id);
}
