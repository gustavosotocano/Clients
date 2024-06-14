package com.gas.client.domain.client;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class ClientDto {

    private String sharedKey;
    @NotNull(message = "It must not be null")
    private String businessId;
    @NotNull(message = "It must not be null")
    private String email;
    @NotNull(message = "It must not be null")
    private String phone;

    private LocalDateTime added;
    @NotNull(message = "It must not be null")
    private LocalDate started;
    @NotNull(message = "It must not be null")
    private LocalDate ended;


}
