package com.gml.domain.implementation;

import com.gml.application.port.inbound.UpdateClientUseCase;
import com.gml.application.port.outbound.ClientPersistencePort;
import com.gml.domain.model.Client;
import lombok.AllArgsConstructor;

import java.util.Date;

@AllArgsConstructor
public class UpdateClientUseCaseImpl implements UpdateClientUseCase {

    private final ClientPersistencePort clientPersistencePort;

    @Override
    public Client updateClient(Client client) {

        Client client1 = Client.builder()
                .sharedKey(client.sharedKey())
                .added(new Date())
                .email(client.email())
                .phone(client.phone())
                .started(client.started())
                .ended(client.ended())
                .bussinessId(client.bussinessId())
                .build();
        return clientPersistencePort.updateClient(client1);
    }
}
