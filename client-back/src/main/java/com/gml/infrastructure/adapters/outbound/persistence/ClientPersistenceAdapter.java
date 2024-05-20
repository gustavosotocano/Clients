package com.gml.infrastructure.adapters.outbound.persistence;

import com.gml.application.port.outbound.ClientPersistencePort;

import com.gml.domain.model.Client;
import com.gml.infrastructure.adapters.outbound.persistence.mapper.ClientPersistenceMapper;
import com.gml.infrastructure.adapters.outbound.persistence.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
public class ClientPersistenceAdapter implements ClientPersistencePort {

    private final ClientRepository clientRepository;
    private final ClientPersistenceMapper clientPersistenceMapper =
            Mappers.getMapper(ClientPersistenceMapper.class);


    @Override
    public Client saveClient(Client client) {
        return clientPersistenceMapper.toModel(clientRepository.save(clientPersistenceMapper.toEntity(client)));
    }

    @Override
    public Optional<Client> getClientById(String id) {
        return clientRepository.findById(id).map(clientPersistenceMapper::toModel);
    }

    @Override
    public  List<Client> getClientByAll() {
        return clientRepository.findAll()
                .stream().map(clientPersistenceMapper::toModel).toList();
    }

    @Override
    public Optional<Client> findByEmail(String email) {
        return clientRepository.findByEmail(email).map(clientPersistenceMapper::toModel);
    }

}