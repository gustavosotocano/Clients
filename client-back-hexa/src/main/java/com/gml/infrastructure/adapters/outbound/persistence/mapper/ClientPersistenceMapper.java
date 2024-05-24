package com.gml.infrastructure.adapters.outbound.persistence.mapper;

import com.gml.domain.model.Client;
import com.gml.infrastructure.adapters.outbound.persistence.entity.ClientEntity;
import org.mapstruct.Mapper;

@Mapper
public interface ClientPersistenceMapper {

    ClientEntity toEntity(Client client);

    Client toModel(ClientEntity clientEntity);
}
