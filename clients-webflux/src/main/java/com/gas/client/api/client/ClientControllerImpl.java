package com.gas.client.api.client;

import com.gas.client.api.response.Response;
import com.gas.client.domain.client.ClientDto;
import com.gas.client.domain.client.ClientService;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@RestController
public class ClientControllerImpl implements ClientController{

    private final ClientService clientService;

    public ClientControllerImpl(ClientService clientService) {
        this.clientService = clientService;
    }



    @Override
    public Mono<Response> findAll() {
        return clientService.getClients()
                .map(mapToClientControllerResponseDto())
                .collectList()
                .map(Response.ok()::setPayload)
                .cast(Response.class);
    }

    @Override
    public Mono<Response> getClientByEmail(String email) {
        return clientService.getClientByEmail(email)
                .map(Response.ok()::setPayload)
                .cast(Response.class);
    }

    @Override
    public Mono<Response> getClientById(String sharedKey) {
           return clientService.getClientById(sharedKey)
             .map(Response.ok()::setPayload)
                   .cast(Response.class);

              

    }

    @Override
    public Mono<Response> addClient(CreateClientRequest createClientRequest) {
        return clientService.addCLient(mapToClientDto().apply(createClientRequest))
                .map(mapToClientControllerResponseDto())
                .map(Response.ok()::setPayload)
                .cast(Response.class);
    }

    @Override
    public ResponseEntity<Object> updClient(ClientDto clientDto) {
        return null;
    }

    @Override
    public void exportCSV(HttpServletResponse response) {
        String fileName = "employee-data.csv";

        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName);

        StatefulBeanToCsv<ClientControllerResponseDto> writer = null;
        try {
            writer = new StatefulBeanToCsvBuilder<ClientControllerResponseDto>(response.getWriter())
                    .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                    .withOrderedResults(true)
                    .build();

            var listClients = clientService.getClients();
            if(listClients != null) {
           /*     List<ClientControllerResponseDto> clientListResponse = listClients.map(mapToClientControllerResponseDto())
                        .collect(Collectors.toList()).block();
*/
                writer.write(listClients.map(mapToClientControllerResponseDto())
                                     .collect(Collectors.toList()).block());
            }
        } catch (IOException | CsvRequiredFieldEmptyException | CsvDataTypeMismatchException e) {
            throw new RuntimeException(e);
        } finally {
            if(writer != null) {
                try {
                    //writer.close();
                } catch (Exception ex) {
                    //Handle exception or rethrow
                }
            }
        }


    }

    @Override
    public Function<ClientDto, ClientControllerResponseDto> mapToClientControllerResponseDto() {
        return clientDto ->
                ClientControllerResponseDto.builder()
                        .sharedKey(clientDto.getSharedKey())
                        .bussinessId(clientDto.getBusinessId())
                        .phone(clientDto.getPhone())
                        .email(clientDto.getEmail().trim())
                        .added(clientDto.getAdded())
                        .started(clientDto.getStarted())
                        .ended(clientDto.getEnded())

                        .build();
    }
    @Override
    public Function<CreateClientRequest, ClientDto> mapToClientDto() {


        return (clientRequestDto ->{
            String[] names = clientRequestDto.bussinessId().split(" ");
            String initial = names[0].substring(0, 1);


            String lastName;
            if (names.length == 1) {
                lastName = names[0];
            } else if (names.length == 2) {
                lastName = names[1];
            } else if (names.length > 2) {
                lastName = names[2];
            } else {
                lastName = "";
            }


            return ClientDto.builder()
                    .sharedKey((initial+lastName).toLowerCase(Locale.ROOT))
                    .businessId(clientRequestDto.bussinessId())
                    .phone(clientRequestDto.phone())
                    .started(clientRequestDto.started())
                    .ended(clientRequestDto.ended())
                    .added(  LocalDateTime.now())
                    .email(clientRequestDto.email())
                    .build();
        });

    }
}
