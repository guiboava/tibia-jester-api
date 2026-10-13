package io.github.guiboava.tibiajesterapi.service;

import io.github.guiboava.tibiajesterapi.controller.dto.request.ClientRequestDTO;
import io.github.guiboava.tibiajesterapi.controller.dto.response.ClientResponseDTO;
import io.github.guiboava.tibiajesterapi.controller.mappers.ClientMapper;
import io.github.guiboava.tibiajesterapi.entity.model.Client;
import io.github.guiboava.tibiajesterapi.repository.ClientRepository;
import io.github.guiboava.tibiajesterapi.validator.ClientValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository repository;
    private final ClientMapper mapper;
    private final ClientValidator validator;
    private final PasswordEncoder encoder;

    public ClientResponseDTO save(ClientRequestDTO dto) {

        Client client = mapper.toEntity(dto);

        String clientId = UUID.randomUUID().toString();
        String plainSecret = UUID.randomUUID().toString();

        client.setClientId(clientId);

        validator.validate(client);

        client.setClientSecret(encoder.encode(plainSecret));
        repository.save(client);

        return new ClientResponseDTO(client.getName(), clientId, plainSecret);
    }

    public Client getByClientId(String clientId) {
        return repository.findByClientId(clientId);
    }
}