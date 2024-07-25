package com.gml.infrastructure.adapters.inbound.rest;

import com.gml.application.port.inbound.CreateClientUseCase;
import com.gml.application.port.inbound.GetClientUseCase;
import com.gml.application.port.inbound.UpdateClientUseCase;
import com.gml.domain.model.Client;
import com.gml.infrastructure.adapters.inbound.rest.mapper.ClientRestMapper;
import com.gml.infrastructure.adapters.inbound.rest.request.ClientRequestPut;
import com.gml.infrastructure.adapters.inbound.rest.request.CreateClientRequest;
import com.gml.infrastructure.adapters.inbound.rest.response.CreateClientResponse;
import com.gml.infrastructure.adapters.inbound.rest.response.GetClientResponse;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 360000)
@RestController
@RequiredArgsConstructor
public class ClientRestAdapter {

    private final CreateClientUseCase createClientUseCase;
    private final UpdateClientUseCase updateClientUseCase;

    private final GetClientUseCase getClientUseCase;
    private final ClientRestMapper clientRestMapper = Mappers.getMapper(ClientRestMapper.class);

    @PostMapping("/v1/clients")
    public ResponseEntity<CreateClientResponse> createClient(@Valid @RequestBody CreateClientRequest createClientRequest) {

        Client client = createClientUseCase.createClient(clientRestMapper.toModel(createClientRequest));

        return new ResponseEntity<>(clientRestMapper.toCreateClientResponse(client), HttpStatus.CREATED);
    }

/*
    @PutMapping("/v1/clients")
    public ResponseEntity<CreateClientResponse> updateClient(@Valid @RequestBody CreateClientRequest
    createClientRequest) {
        Client client = updateClientUseCase.updateClient(clientRestMapper.toModel(createClientRequest));
        return new ResponseEntity<>(clientRestMapper.toCreateClientResponse(client), HttpStatus.CREATED);
    }
*/

    @PutMapping("/v1/clients/{sharedKey}")
    public ResponseEntity<CreateClientResponse> update(@Valid @RequestBody ClientRequestPut clientRequestPut,
                                                       @PathVariable String sharedKey) {

        Client client = updateClientUseCase.updateClient(clientRequestPut, sharedKey);

        return new ResponseEntity<>(clientRestMapper.toCreateClientResponse(client), HttpStatus.CREATED);
    }


    @GetMapping(value = "/v1/client/sharedKey/{sharedKey}")
    public ResponseEntity<GetClientResponse> getClientById(@PathVariable String sharedKey) {
        Client client = getClientUseCase.getClientById(sharedKey);
        return new ResponseEntity<>(clientRestMapper.toGetClientResponse(client), HttpStatus.OK);
    }

    @GetMapping(value = "/v1/client")
    public ResponseEntity<List<GetClientResponse>> getClients() {
        List<Client> clients = getClientUseCase.getClientByAll();
        var client = clients.stream().map(clientRestMapper::toGetClientResponse).toList();

        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @GetMapping(value = "/v1/client/email/{email}")
    public ResponseEntity<List<GetClientResponse>> getMail(@PathVariable String email) {
        Optional<Client> clients = getClientUseCase.getClientByEmail(email);
        var client = clients.stream().map(clientRestMapper::toGetClientResponse).toList();

        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @GetMapping("/v1/exportCSV")
    public void exportCSV(HttpServletResponse response) throws CsvDataTypeMismatchException,
            CsvRequiredFieldEmptyException, IOException {
        String fileName = "employee-data.csv";

        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName);

        StatefulBeanToCsv<Client> writer = new StatefulBeanToCsvBuilder<Client>(response.getWriter())
                .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                .withOrderedResults(true)
                .build();

        writer.write(getClientUseCase.getClientByAll());
    }
}
