package com.gml.infraestructure.adapters.inbound.rest.testobjectmother;

import com.gml.infrastructure.adapters.inbound.rest.request.CreateClientRequest;

public class CreateClientRequestTestObjectMother {
    public static CreateClientRequest aCreateClientRequest() {
        return CreateClientRequest.builder()
                .sharedKey("gsoto")
                .bussinessId("name")
                .email("description@gmail.com")
                .phone("34567776")
                .build();
    }
}
