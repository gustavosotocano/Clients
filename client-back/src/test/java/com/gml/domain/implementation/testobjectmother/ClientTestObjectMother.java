package com.gml.domain.implementation.testobjectmother;

import com.gml.domain.model.Client;

public class ClientTestObjectMother {
    public static Client aClient() {
        return Client.builder()
                .sharedKey("gsoto")
                .bussinessId("name")
                .email("description@gmail.com")
                .phone("34567776")
                .build();
    }

}
