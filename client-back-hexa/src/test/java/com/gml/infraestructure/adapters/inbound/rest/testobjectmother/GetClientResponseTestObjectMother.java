package com.gml.infraestructure.adapters.inbound.rest.testobjectmother;

import com.gml.infrastructure.adapters.inbound.rest.response.GetClientResponse;

public class GetClientResponseTestObjectMother {

    public static GetClientResponse aGetClientResponse() {
        return GetClientResponse.builder()
                .sharedKey("gsoto")
                .bussinessId("name")
                .email("description@gmail.com")
                .phone("34567776")
                .build();
    }
}
