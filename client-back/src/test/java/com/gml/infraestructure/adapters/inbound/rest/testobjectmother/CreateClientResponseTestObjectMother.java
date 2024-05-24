package com.gml.infraestructure.adapters.inbound.rest.testobjectmother;

import com.gml.infrastructure.adapters.inbound.rest.response.CreateClientResponse;

public class CreateClientResponseTestObjectMother {

    public static CreateClientResponse aCreateFruitResponse() {
        return CreateClientResponse.builder()
                .sharedKey("gsoto")
                .bussinessId("name")
                .email("description@gmail.com")
                .phone("34567776")
                .build();
    }
}
