package com.gas.client.domain.client;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ClientPersistencePort {

    Flux<ClientDto> getAllClients();
    Mono<ClientDto> saveClient(ClientDto clientDto);
   Mono<ClientDto> getClientByEmail(String email);
   Mono<ClientDto> getClientById(String sharedKey);
}
