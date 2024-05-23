package com.gml.implementation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gml.application.port.inbound.CreateClientUseCase;
import com.gml.application.port.inbound.GetClientUseCase;
import com.gml.domain.model.Client;
import com.gml.infrastructure.adapters.inbound.rest.ClientRestAdapter;
import com.gml.infrastructure.adapters.inbound.rest.mapper.ClientRestMapper;
import com.gml.infrastructure.adapters.inbound.rest.response.GetClientResponse;
import com.gml.util.Util;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MvcResult;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


//@WebMvcTest
public class ClientControllerTest {


/*@Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientRepository clientJpaRepository;
*/
    private final GetClientUseCase getClientUseCase = mock(GetClientUseCase.class);
    private final CreateClientUseCase createClientUseCase = mock(CreateClientUseCase.class);
    private final ClientRestMapper clientRestMapper = Mappers.getMapper(ClientRestMapper.class);

    private final ClientRestAdapter clientRestAdapter = new ClientRestAdapter(createClientUseCase, getClientUseCase);
    @Test
    public void findAllTest()   {

        //GIVEN
        when(getClientUseCase.getClientByAll()).thenReturn(getClientDtos());
        //WHEN
        ResponseEntity<List<GetClientResponse>> testResponse = clientRestAdapter.getClients();

        //  RequestBuilder request= MockMvcRequestBuilders

        //THEN
        verify(getClientUseCase, times(1))
                .getClientByAll();

       List<GetClientResponse> objeto =testResponse.getBody();
        Assertions.assertAll("test",

                () -> assertEquals(HttpStatus.OK, testResponse.getStatusCode()),
                () -> assertNotNull(testResponse.getBody()),
                () -> assertEquals(objeto.size(), 1, "Cantidad debe ser 1"),
                () -> assertEquals(objeto.get(0).bussinessId() , "John Doe", "Business Id no es igual"));
    }


    private List<Client> getClientDtos() {
        List<Client> clients = new ArrayList<>();
        clients.add(
                new Client( "jdoe","John Doe" ,"jdoe@gmail.com",
                        "1111111111",  Util.stringToDate( "Jun-07-2013"),
                        null,Util.stringToDate("Jul-07-2013"))
        );

        return clients;
    }

    private List<GetClientResponse> stringToObject(MvcResult result) throws JsonProcessingException, UnsupportedEncodingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
        });
    }

    @Test
    public void findAllNotFoundTest() throws Exception {

        //GIVEN
        when(getClientUseCase.getClientByAll()).thenReturn(Collections.emptyList());

        //WHEN
        ResponseEntity<List<GetClientResponse>> testResponse = clientRestAdapter.getClients();


        //  RequestBuilder request= MockMvcRequestBuilders

        Assertions.assertAll("test",

                () -> assertEquals(HttpStatus.OK, testResponse.getStatusCode()),
                () -> assertEquals(true, testResponse.getStatusCode().is2xxSuccessful(), "Esperamos una lista vacia"));
    }
/*
    @Test
    public void findbyEmailNfotFoundTest() throws Exception {

        /*
        Mockito.when(personService.findByEmail("C")).thenReturn(getPersonaDtos());
        //  RequestBuilder request= MockMvcRequestBuilders
        var result = mockMvc.perform(get("/persona/C/dcto/234"))

                .andExpect(status().isOk())
                .andReturn();
        List<ClienteDto> objeto = stringToObject(result);
        Assertions.assertAll("test",
                () -> assertEquals(objeto.get(0).getName(), "segundoNombre", "No tiene personas Registradas"));


    }

    @Test
    public void findbyEmailNotFoundTest() throws Exception {


        Mockito.when(clientService.findByEmail("jdsoe@gmail.com"))
                .thenThrow(new ResourceNotFoundException("001", "Client Not Found"));
        //  RequestBuilder request= MockMvcRequestBuilders
        var result = mockMvc.perform(get("/v1/client/email/jdsoe@gmail.com"))

                .andExpect(status().isNotFound())
                .andReturn();

        Assertions.assertAll("test",
                () -> assertEquals(((ResourceNotFoundException) Objects.requireNonNull(result.getResolvedException())).getMessage()
                        , "Client Not Found", "Client Not Found"));


    }

    @Test
    public void addClientTest() throws Exception {
        String json= """
                {
                "bussinessId":"jhon doe",
                "email":"gustavo@domain.com",
                "phone":"adolfo",
                "started":"2024-05-25",
                "ended":"2024-05-25"
                }
                """;
         mockMvc.perform(post("/v1/client").contentType(MediaType.APPLICATION_JSON)
                        .content(json))

                .andExpect(status().isCreated())
                .andReturn();

    }
    @Test
    public void addClientTrowTest() throws Exception {
        String json= """
                {
                "email":"gustavo@domain.com",
                "phone":"adolfo",
                "added":"2024-05-01",
                "started":"2024-05-25",
                "ended":"2024-05-25",
                "name":"cra 1a113 #72-84"
                }
                """;
       mockMvc.perform(post("/v1/client").contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest())
                .andReturn();

    }

 */
}
