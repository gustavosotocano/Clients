package com.gas.client.api.client;

//import com.gml.domain.exception.anotation.UniqueEmail;
import com.gas.client.domain.annotation.UniqueEmail;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Builder
public record CreateClientRequest(

        String sharedKey,
        @NotNull(message = "It must not be null")
        String bussinessId,

        @NotNull(message = "It must not be null")
        @UniqueEmail(message = "{Unique.user.email}")
        String email,

        @NotNull(message = "It must not be null")
        String phone,

        LocalDateTime added,

        @NotNull(message = "It must not be null")
        LocalDate started,

        @NotNull(message = "It must not be null")
        LocalDate ended){}