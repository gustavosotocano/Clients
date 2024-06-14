package com.gml.application.port.inbound;


import com.gml.domain.model.Client;
import com.gml.infrastructure.adapters.inbound.rest.request.ClientRequestPut;

public interface UpdateClientUseCase {


Client updateClient(ClientRequestPut client, String sharedKey);


}
