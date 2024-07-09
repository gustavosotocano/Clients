package com.gas.service;




import com.gas.dominio.Client;
import com.gas.port.ClientRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import java.util.List;

@ApplicationScoped
public class ClientService {

    @Inject
    ClientRepository clientRepository;

    public List<Client> getAllClients() {
        return clientRepository.getAllClients();
    }

    public Client getClientById(String id) {
        return clientRepository.getClientById(id);
    }

    @Transactional
    public Client addClient(Client client) {

      return  clientRepository.addClient(client);
    }

    public void updateClient(Client client) {
        clientRepository.updateClient(client);
    }

    public void deleteClient(Long id) {
        clientRepository.deleteClient(id);
    }

    public Client getClientByField(String fieldName,String fieldValue) {
        return clientRepository.getClientByField(fieldName,fieldValue);
    }


    public Client getClientByBusinessId(String businessId) {
        return clientRepository.getClientByBusinessId(businessId);
    }
}
