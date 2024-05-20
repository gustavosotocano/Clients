package com.gml.domain.model;

import com.gml.infrastructure.client.unique.UniqueEmail;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Builder

public record Client(

     String sharedKey,
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
     Date ended){

}
