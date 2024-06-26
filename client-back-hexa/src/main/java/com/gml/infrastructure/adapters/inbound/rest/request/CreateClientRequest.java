package com.gml.infrastructure.adapters.inbound.rest.request;

import com.gml.domain.exception.anotation.UniqueEmail;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.Date;
@Builder
public record CreateClientRequest (


        @NotNull(message = "It must not be null")
        String bussinessId,

        @NotNull(message = "It must not be null")
        @UniqueEmail(message = "{Unique.user.email}")
        String email,

        @NotNull(message = "It must not be null")
        String phone,

        Date added,

        @NotNull(message = "It must not be null")
        Date started,

        @NotNull(message = "It must not be null")
        Date ended){}