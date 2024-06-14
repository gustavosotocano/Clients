package com.gas.client.domain.client;


import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ClientService {

    Flux<ClientDto> getClients();
    Mono<ClientDto> addCLient(ClientDto clientDto);
    Mono<ClientDto> getClientByEmail(String email);
    Mono<ClientDto> getClientById(String sharedKey);
}
