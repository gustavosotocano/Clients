package com.gas.dominio.exception.anotation;


import com.gas.dominio.Client;
import com.gas.service.ClientService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Objects;

@ApplicationScoped
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {
    @Inject
     ClientService clientService;

    @Override
    public void initialize(UniqueEmail constraintAnnotation) {
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (clientService != null) {
             Client usuario = clientService.getClientByField("email",email);
            return Objects.isNull(usuario );
        }

        return true;
    }
}