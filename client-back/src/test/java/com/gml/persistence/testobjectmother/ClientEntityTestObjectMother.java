package com.gml.persistence.testobjectmother;


import com.gml.infrastructure.adapters.outbound.persistence.entity.ClientEntity;

public class ClientEntityTestObjectMother {
    public static ClientEntity aClientEntity() {
        return ClientEntity.builder()
                .sharedKey("1L")
                .bussinessId("name")
                .email("description@gmail.com")
                .phone("453453454")
                .build();
    }
}
