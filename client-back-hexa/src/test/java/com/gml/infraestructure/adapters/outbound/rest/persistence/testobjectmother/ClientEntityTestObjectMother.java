package com.gml.infraestructure.adapters.outbound.rest.persistence.testobjectmother;

import com.gml.infrastructure.adapters.outbound.persistence.entity.ClientEntity;

public class ClientEntityTestObjectMother {
    public static ClientEntity aFruitEntity() {
        return ClientEntity.builder()
                .sharedKey("gsoto")
                .bussinessId("name")
                .email("description@gmail.com")
                .phone("34567776")
                .build();
    }
}
