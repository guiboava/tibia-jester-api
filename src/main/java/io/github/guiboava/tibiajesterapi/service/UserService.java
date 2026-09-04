package io.github.guiboava.tibiajesterapi.service;

import io.github.guiboava.tibiajesterapi.controller.dto.UserRequestDTO;
import io.github.guiboava.tibiajesterapi.controller.dto.UserResponseDTO;
import io.github.guiboava.tibiajesterapi.controller.mappers.UserMapper;
import io.github.guiboava.tibiajesterapi.entity.enums.UserType;
import io.github.guiboava.tibiajesterapi.entity.model.User;
import io.github.guiboava.tibiajesterapi.exception.ResourceNotFoundException;
import io.github.guiboava.tibiajesterapi.repository.UserRepository;
import io.github.guiboava.tibiajesterapi.validator.UserValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserValidator userValidator;
    //private final PasswordEncoder encoder;    ATENÇÃO FUTURAMENTE DEVE SER FEITA A CAMADA DE SPRING SECURITY DO APP


    public UUID save(UserRequestDTO dto) {

        User user = userMapper.toEntity(dto);

        userValidator.validate(user);
        //user.setPassword(encoder.encode(user.getPassword()));     ATENÇÃO FUTURAMENTE DEVE SER FEITA A CAMADA DE SPRING SECURITY DO APP
        return userRepository.save(user).getId();

    }

    public void update(UUID userId, UserRequestDTO dto) {

        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado para o id " + userId));
        userMapper.updateEntityFromDto(dto, user);

        userValidator.validate(user);

        if (dto.password() != null && !dto.password().isBlank()) {
            user.setPassword((dto.password()));
        }

        userRepository.save(user);
    }

    public void delete(UUID userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado."));
        userRepository.delete(user);

    }

    public UserResponseDTO getById(UUID userId) {

        return userRepository.findById(userId).map(userMapper::toDTO).orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado nenhum dado de usuário para o paciente."));

    }

    public List<UserResponseDTO> searchByExample(String name, String login, String email, UserType userType) {
        var user = new User();
        user.setName(name);
        user.setLogin(login);
        user.setEmail(email);
        user.setUserType(userType);

        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<User> userExample = Example.of(user, matcher);

        return userRepository.findAll(userExample).stream().map(userMapper::toDTO).toList();

    }
}
