package com.gml.infraestructure.adapters.inbound.rest;

import com.gml.application.port.inbound.CreateClientUseCase;
import com.gml.application.port.inbound.GetClientUseCase;
import com.gml.application.port.inbound.UpdateClientUseCase;
import com.gml.domain.implementation.testobjectmother.ClientTestObjectMother;
import com.gml.infraestructure.adapters.inbound.rest.testobjectmother.CreateClientRequestTestObjectMother;
import com.gml.infraestructure.adapters.inbound.rest.testobjectmother.CreateClientResponseTestObjectMother;
import com.gml.infraestructure.adapters.inbound.rest.testobjectmother.GetClientResponseTestObjectMother;
import com.gml.infrastructure.adapters.inbound.rest.ClientRestAdapter;
import com.gml.infrastructure.adapters.inbound.rest.mapper.ClientRestMapper;
import com.gml.infrastructure.adapters.inbound.rest.response.CreateClientResponse;

import com.gml.infrastructure.adapters.inbound.rest.response.GetClientResponse;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class ClientRestAdapterTest {

    private final CreateClientUseCase createFruitUseCase = mock(CreateClientUseCase.class);
    private final GetClientUseCase getClientUseCase = mock(GetClientUseCase.class);
    private final UpdateClientUseCase updateClientUseCase= mock(UpdateClientUseCase.class);

    private final ClientRestAdapter clientRestAdapterTest = new ClientRestAdapter(createFruitUseCase,updateClientUseCase, getClientUseCase);
    private final ClientRestMapper fruitRestMapper = Mappers.getMapper(ClientRestMapper.class);
    private static final String SHARED_KEY="rtert";

    @Test
    public void createFruitTest() {
        //GIVEN
        when(createFruitUseCase.createClient(fruitRestMapper.toModel(CreateClientRequestTestObjectMother.aCreateClientRequest())))
                .thenReturn(ClientTestObjectMother.aClient());

        //WHEN
        ResponseEntity<CreateClientResponse> testResponse = clientRestAdapterTest.createClient(CreateClientRequestTestObjectMother.aCreateClientRequest());

        //THEN
        verify(createFruitUseCase, times(1))
                .createClient(fruitRestMapper.toModel(CreateClientRequestTestObjectMother.aCreateClientRequest()));
        assertEquals(HttpStatus.CREATED, testResponse.getStatusCode());
        assertNotNull(testResponse.getBody());
        assertEquals(CreateClientResponseTestObjectMother.aCreateFruitResponse(), testResponse.getBody());
    }

    @Test
    public void getFruitTest() {
        //GIVEN
        when(getClientUseCase.getClientById(SHARED_KEY)).thenReturn(ClientTestObjectMother.aClient());

        //WHEN
        ResponseEntity<GetClientResponse> testResponse = clientRestAdapterTest.getClientById(SHARED_KEY);

        //THEN
        verify(getClientUseCase, times(1)).getClientById(SHARED_KEY);
        assertEquals(HttpStatus.OK, testResponse.getStatusCode());
        assertNotNull(testResponse.getBody());
        assertEquals(GetClientResponseTestObjectMother.aGetClientResponse(), testResponse.getBody());
    }
}
