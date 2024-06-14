package com.gas.client.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Builder
public class ResponseError {
    @JsonProperty("errorCode")
    String errorCode;
    @JsonProperty("message")
    String message;
}
