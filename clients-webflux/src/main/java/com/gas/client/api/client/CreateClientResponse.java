package com.gas.client.api.client;

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