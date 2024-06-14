package com.gml.domain.implementation;

import com.gml.application.port.outbound.ClientPersistencePort;
import com.gml.application.port.inbound.CreateClientUseCase;

import com.gml.domain.model.Client;
import lombok.AllArgsConstructor;

import java.util.Date;
import java.util.Locale;

@AllArgsConstructor
public class CreateClientUseCaseImpl implements CreateClientUseCase {

    private final ClientPersistencePort clientPersistencePort;

    @Override
    public Client createClient(Client client) {
/*
        var clients = clientPersistencePort.findByEmail(client.email());
        if (Objects.nonNull(clients)) {
            throw new RequestException("004", "Client is already created");
        }
        */
        String[] names = client.businessId().split(" ");
        String lastName = "";
        if (names.length == 1) {
            lastName = names[0];
        } else if (names.length == 2) {
            lastName = names[1];
        } else if (names.length > 2) {
            lastName = names[2];
        }

        String initial = names[0].substring(0, 1);

Client client1= Client.builder()
        .sharedKey((initial+lastName).toLowerCase(Locale.ROOT))
        .added(new Date())
        .email(client.email())
        .phone(client.phone())
        .started(client.started())
        .ended(client.ended())
        .businessId(client.businessId())
        .build();
        return clientPersistencePort.saveClient(client1);
    }
}
