package com.gml.infrastructure.adapters.inbound.rest.response;

import lombok.Builder;

import java.util.Date;

@Builder
public record CreateClientResponse (
        String sharedKey,
        String bussinessId,
        String email,
        String phone,
        Date added,
        Date started,
        Date ended){}