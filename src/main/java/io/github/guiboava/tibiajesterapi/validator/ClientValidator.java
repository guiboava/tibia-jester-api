package io.github.guiboava.tibiajesterapi.validator;

import io.github.guiboava.tibiajesterapi.entity.model.Client;
import io.github.guiboava.tibiajesterapi.exception.BusinessException;
import io.github.guiboava.tibiajesterapi.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClientValidator {

    private final ClientRepository repository;

    public void validate(Client client) {

        if (repository.existsByName(client.getName())) {
            throw new BusinessException("Já existe um client cadastrado com esse nome.");
        }

    }

}