package com.gas.client.api.client;

import com.gas.client.api.response.Response;
import com.gas.client.domain.client.ClientDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletResponse;

import reactor.core.publisher.Mono;

import java.util.function.Function;


public interface ClientController {

    @GetMapping("/v1/client")
    Mono<Response> findAll();

    @PostMapping("/v1/client")
      Mono<Response> addClient(@Valid @RequestBody CreateClientRequest clientDto);



    @GetMapping("/v1/client/email/{email}")
    Mono<Response> getClientByEmail(@Valid @NotNull @PathVariable String email);

    @GetMapping("/v1/client/id/{sharedKey}")
    Mono<Response> getClientById(@Valid @NotNull @PathVariable String sharedKey);


    @PutMapping("/v1/client")
    ResponseEntity<Object> updClient(@Valid @RequestBody ClientDto clientDto);

    @GetMapping("/v1/exportCSV")
    void exportCSV(HttpServletResponse response);

      Function<ClientDto, ClientControllerResponseDto> mapToClientControllerResponseDto();

     Function<CreateClientRequest, ClientDto> mapToClientDto() ;
}
