package com.gml.domain.model;

import com.gml.domain.exception.anotation.UniqueEmail;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.Date;

@Builder

public record Client(

        String sharedKey,
        @NotNull(message = "It must not be null")
        String businessId,

        @NotNull(message = "It must not be null")
        @UniqueEmail(message = "{Unique.user.email}")
        String email,

        @NotNull(message = "It must not be null")
        String phone,

        Date added,

        @NotNull(message = "It must not be null")
        Date started,

        @NotNull(message = "It must not be null")
        Date ended) {

}
