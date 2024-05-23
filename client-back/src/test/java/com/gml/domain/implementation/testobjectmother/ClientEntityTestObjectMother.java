package com.gml.domain.implementation.testobjectmother;


import com.gml.infrastructure.adapters.outbound.persistence.entity.ClientEntity;

public class ClientEntityTestObjectMother {
    public static ClientEntity aClientEntity() {
        return ClientEntity.builder()
                .sharedKey("gsoto")
                .bussinessId("name")
                .email("description@gmail.com")
                .phone("34567776")
                .build();
    }
}
