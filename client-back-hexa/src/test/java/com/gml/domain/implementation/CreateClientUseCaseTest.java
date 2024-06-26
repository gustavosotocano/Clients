package com.gml.domain.implementation;

import com.gml.domain.model.Client;
import com.gml.domain.implementation.testobjectmother.ClientTestObjectMother;
import com.gml.infrastructure.adapters.outbound.persistence.ClientPersistenceAdapter;
import com.gml.infrastructure.adapters.outbound.persistence.repository.ClientRepository;
import com.gml.domain.implementation.testobjectmother.ClientEntityTestObjectMother;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CreateClientUseCaseTest {
    private final ClientRepository clientRepository = mock(ClientRepository.class);
    private final ClientPersistenceAdapter clientPersistenceAdapterTest = new ClientPersistenceAdapter(clientRepository);



    @Test
    public void CreateClientTest(){

        //GIVEN
        when(clientRepository.save(ClientEntityTestObjectMother.aClientEntity()))
                .thenReturn(ClientEntityTestObjectMother.aClientEntity());

        //WHEN
        Client testClient = clientPersistenceAdapterTest.saveClient(ClientTestObjectMother.aClient());

        //THEN
        assertEquals(ClientTestObjectMother.aClient(), testClient);
    }

}
