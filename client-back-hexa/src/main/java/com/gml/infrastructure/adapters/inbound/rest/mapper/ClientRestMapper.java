package com.gml.infrastructure.adapters.inbound.rest.mapper;

import com.gml.domain.model.Client;
import com.gml.infrastructure.adapters.inbound.rest.request.ClientRequestPut;
import com.gml.infrastructure.adapters.inbound.rest.request.CreateClientRequest;
import com.gml.infrastructure.adapters.inbound.rest.response.CreateClientResponse;
import com.gml.infrastructure.adapters.inbound.rest.response.GetClientResponse;
import org.mapstruct.Mapper;



@Mapper
public interface ClientRestMapper {

    Client toModel(CreateClientRequest createclientRequest);
    Client toModelPut(ClientRequestPut clientRequestPut);
    CreateClientResponse toCreateClientResponse(Client client);
    GetClientResponse toGetClientResponse(Client client);

}
