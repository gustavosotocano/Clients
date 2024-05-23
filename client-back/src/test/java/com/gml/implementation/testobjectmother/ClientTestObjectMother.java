package com.gml.implementation.testobjectmother;

import com.gml.domain.model.Client;

public class ClientTestObjectMother {
    public static Client aClient() {
        return Client.builder()
                .sharedKey("1L")
                .bussinessId("name")
                .email("description@gmail.com")
                .phone("453453454")
                .build();
    }

}
