package com.gml.application.port.inbound;

import com.gml.domain.exception.ClientNotFoundException;
import com.gml.domain.model.Client;

import java.util.List;
import java.util.Optional;

public interface GetClientUseCase {

    Client getClientById(String id) throws ClientNotFoundException;
    List<Client> getClientByAll();
    Optional<Client> getClientByEmail(String eMail) throws ClientNotFoundException;
}
