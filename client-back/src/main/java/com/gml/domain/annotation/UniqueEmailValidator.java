package com.gml.domain.annotation;

import com.gml.application.port.inbound.GetClientUseCase;
import com.gml.domain.model.Client;
import com.gml.infrastructure.client.unique.UniqueEmail;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    private final GetClientUseCase getClientUseCase;

    public UniqueEmailValidator(GetClientUseCase getClientUseCase) {
        this.getClientUseCase = getClientUseCase;
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (getClientUseCase != null) {
            Optional  <Client> usuario = getClientUseCase.getClientByEmail(email);
            return  usuario.isEmpty();
        }

        return true;
    }
}