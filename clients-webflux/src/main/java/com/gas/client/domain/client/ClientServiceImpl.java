package com.gas.client.domain.client;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientPersistencePort clientPersistencePort;

    public ClientServiceImpl(ClientPersistencePort clientPersistencePort) {
        this.clientPersistencePort = clientPersistencePort;
    }


    @Override
    public Flux<ClientDto> getClients() {
        return clientPersistencePort.getAllClients();
    }

    @Override
    public Mono<ClientDto> addCLient(ClientDto clientDto) {

             return clientPersistencePort.saveClient(clientDto);
    }

    @Override
    public Mono<ClientDto> getClientByEmail(String email) {

        return clientPersistencePort.getClientByEmail(email);
    }


    @Override
    public Mono<ClientDto> getClientById(String sharedKey) {
        return clientPersistencePort.getClientById(sharedKey) ;
    }


}
