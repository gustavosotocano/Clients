package com.gml.infrastructure.adapters.inbound.rest.response;

import java.util.Date;

public record CreateClientResponse (
        String sharedKey,
        String bussinessId,
        String email,
        String phone,
        Date added,
        Date started,
        Date ended){}