package com.gml.infrastructure.adapters.binding;

import com.gml.domain.implementation.CreateClientUseCaseImpl;

import com.gml.domain.implementation.GetClientUseCaseImpl;
import com.gml.infrastructure.adapters.outbound.persistence.ClientPersistenceAdapter;
import com.gml.infrastructure.adapters.outbound.persistence.repository.ClientRepository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanBinder {

    @Bean
    public ClientPersistenceAdapter clientPersistenceAdapter(ClientRepository fruitRepository) {
        return new ClientPersistenceAdapter(fruitRepository);
    }

    @Bean
    public CreateClientUseCaseImpl createClientUseCaseImpl(ClientPersistenceAdapter  clientPersistenceAdapter) {
        return new CreateClientUseCaseImpl(clientPersistenceAdapter);
    }

    @Bean
    public GetClientUseCaseImpl getClientUseCaseImpl(ClientPersistenceAdapter clientPersistenceAdapter) {
        return new GetClientUseCaseImpl(clientPersistenceAdapter);
    }


}
