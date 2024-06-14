package com.gas.client.domain.annotation;

import com.gas.client.domain.client.ClientDto;
import com.gas.client.domain.client.ClientService;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Objects;


@Component
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    private final ClientService clientService;

    public UniqueEmailValidator(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        Boolean result =  existsBusinessIdForClientWithEmail(email).block();
        return result != null ? result : true;
    }

    private Mono<Boolean> existsBusinessIdForClientWithEmail(String email) {
        return clientService.getClientByEmail(email)
                .flatMap(clientDto -> {
                    // Perform some side-effect operation here based on the clientDto

                    if (clientDto == null) {
                        System.out.println("No client found with the given email!");

                    } else {
                        System.out.println("Client found!");
                    }
                    return Mono.just(Objects.isNull(clientDto));
                }).   switchIfEmpty(Mono.just(true));


    }

    private boolean hasNonNullBusinessId(ClientDto client) {
        return Objects.nonNull(client.getBusinessId());
    }
}
