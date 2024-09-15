package com.ciandt.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class UserDto {

    private long id;
    private String firstName;
    @NotNull(message = "It must not be null")
    private String lastName;
    @NotNull(message = "It must not be null")
    private String emailAddress;

}
