package com.gml.domain.implementation;

import com.gml.application.port.inbound.UpdateClientUseCase;
import com.gml.application.port.outbound.ClientPersistencePort;
import com.gml.domain.exception.ClientNotFoundException;
import com.gml.domain.model.Client;
import com.gml.infrastructure.adapters.inbound.rest.request.ClientRequestPut;
import lombok.AllArgsConstructor;

import java.util.Date;

@AllArgsConstructor
public class UpdateClientUseCaseImpl implements UpdateClientUseCase {

    private final ClientPersistencePort clientPersistencePort;

    @Override
    public Client updateClient(ClientRequestPut client, String sharedKey) {

       var clients =clientPersistencePort.getClientById(sharedKey)
               .orElseThrow(()-> new ClientNotFoundException("The client Does not be Found "+ sharedKey));

        Client client1 = Client.builder()
                .sharedKey(clients.sharedKey())
                .businessId(clients.businessId())
                .added(clients.added())
                .email(client.email())
                .phone(client.phone())
                .started(client.started())
                .ended(clients.ended())

                .build();
        return clientPersistencePort.updateClient(client1,sharedKey);
    }
}
