package com.gas.client.api.client;

import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
@Builder
public record ClientControllerResponseDto
        (
        String sharedKey,
        String bussinessId,
        String email,
        String phone,
        LocalDateTime added,
        LocalDate started,
        LocalDate ended){}