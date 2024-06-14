package com.gml.application.port.outbound;


import com.gml.domain.model.Client;

import java.util.List;
import java.util.Optional;

public interface ClientPersistencePort {

    Client saveClient(Client client);
    Client updateClient(Client client,String sharedKey);

    Optional<Client> findByEmail(String email);
    Optional<Client> getClientById(String sharedKey);

    List<Client> getClientByAll();
}
