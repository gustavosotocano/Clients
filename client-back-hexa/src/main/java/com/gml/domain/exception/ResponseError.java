package com.gml.domain.exception;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Setter;

@Setter
@Builder
public class ResponseError {
    @JsonProperty("errorCode")
    String errorCode;
    @JsonProperty("message")
    String message;

    public ResponseError() {

        String dato="fsdfsdf";
    }

    public ResponseError(String message, String errorCode) {
        this.message = message;
        this.errorCode = errorCode;
    }
}
