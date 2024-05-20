package com.gml.domain.implementation;

import com.gml.application.port.outbound.ClientPersistencePort;
import com.gml.application.port.inbound.GetClientUseCase;
import com.gml.domain.exception.ClientNotFoundException;
import com.gml.domain.model.Client;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;


@AllArgsConstructor
public class GetClientUseCaseImpl implements GetClientUseCase {

    private final ClientPersistencePort clientPersistencePort;
    @Override
    public Client getClientById(String id) {
        return clientPersistencePort.getClientById(id)
                .orElseThrow(() -> new ClientNotFoundException(String.format("Client not found [id]: %s", id)));
    }

    @Override
    public List<Client> getClientByAll() {
        return clientPersistencePort.getClientByAll();
    }

    @Override
    public Optional<Client> getClientByEmail(String eMail) throws ClientNotFoundException {
        return clientPersistencePort.findByEmail(eMail);


    }

}
