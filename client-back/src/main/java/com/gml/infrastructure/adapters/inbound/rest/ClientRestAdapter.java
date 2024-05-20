package com.gml.infrastructure.adapters.inbound.rest;

import com.gml.application.port.inbound.CreateClientUseCase;
import com.gml.application.port.inbound.GetClientUseCase;
import com.gml.domain.model.Client;
import com.gml.infrastructure.adapters.inbound.rest.mapper.ClientRestMapper;

import com.gml.infrastructure.adapters.inbound.rest.request.CreateClientRequest;
import com.gml.infrastructure.adapters.inbound.rest.response.CreateClientResponse;
import com.gml.infrastructure.adapters.inbound.rest.response.GetClientResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 360000)
@RestController
@RequiredArgsConstructor
public class ClientRestAdapter {

    private final CreateClientUseCase createClientUseCase;
    private final GetClientUseCase getClientUseCase;
    private final ClientRestMapper clientRestMapper = Mappers.getMapper(ClientRestMapper.class);

    @PostMapping("/v1/client")
    public ResponseEntity<CreateClientResponse> addClient(@Valid @RequestBody CreateClientRequest createClientRequest) {

        Client client = createClientUseCase.createClient(clientRestMapper.toModel(createClientRequest));

        return new ResponseEntity<>(clientRestMapper.toCreateClientResponse(client), HttpStatus.CREATED);
    }

    @GetMapping(value = "/v1/client/id/{id}")
    public ResponseEntity<GetClientResponse> getClientById(@PathVariable String id) {
        Client client = getClientUseCase.getClientById(id);
        return new ResponseEntity<>(clientRestMapper.toGetClientResponse(client), HttpStatus.OK);
    }
    @GetMapping(value = "/v1/client")
    public ResponseEntity<List<GetClientResponse>> getClients() {
        List<Client> clients = getClientUseCase.getClientByAll();
        var client=clients.stream().map(clientRestMapper::toGetClientResponse).toList();

        return new ResponseEntity<>( client, HttpStatus.OK);
    }
    @GetMapping(value = "/v1/client/email/{email}")
    public ResponseEntity<List<GetClientResponse>> getMail(@PathVariable String email) {
        Optional<Client> clients = getClientUseCase.getClientByEmail(email);
        var client=clients.stream().map(clientRestMapper::toGetClientResponse).toList();

        return new ResponseEntity<>( client, HttpStatus.OK);
    }
}
