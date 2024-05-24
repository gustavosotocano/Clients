package com.gml.infraestructure.adapters.outbound.rest.persistence;

import com.gml.domain.implementation.testobjectmother.ClientTestObjectMother;
import com.gml.domain.model.Client;
import com.gml.infraestructure.adapters.outbound.rest.persistence.testobjectmother.ClientEntityTestObjectMother;
import com.gml.infrastructure.adapters.outbound.persistence.ClientPersistenceAdapter;
import com.gml.infrastructure.adapters.outbound.persistence.repository.ClientRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ClientPersistenceAdapterTest {

    private final ClientRepository clientRepository = mock(ClientRepository.class);
    private final ClientPersistenceAdapter clientPersistenceAdapterTest = new ClientPersistenceAdapter(clientRepository);

    private static final String SHARED_KEY = "gcano";

    @Test
    public void CreateFruitTest() {
        //GIVEN
        when(clientRepository.save(ClientEntityTestObjectMother.aFruitEntity()))
                .thenReturn(ClientEntityTestObjectMother.aFruitEntity());

        //WHEN
        Client testClient = clientPersistenceAdapterTest.saveClient(ClientTestObjectMother.aClient());

        //THEN
        assertEquals(ClientTestObjectMother.aClient(), testClient);
    }

    @Test
    public void GetFruitByIdTest() {
        //GIVEN
        when(clientRepository.findById(SHARED_KEY)).thenReturn(Optional.of(ClientEntityTestObjectMother.aFruitEntity()));

        //WHEN
        Optional<Client> testFruit = clientPersistenceAdapterTest.getClientById(SHARED_KEY);

        //THEN
        assertTrue(testFruit.isPresent());
        assertEquals(ClientTestObjectMother.aClient(), testFruit.get());
    }

}
