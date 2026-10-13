package io.github.guiboava.tibiajesterapi.service;

import io.github.guiboava.tibiajesterapi.controller.dto.request.BossAlarmRequestDTO;
import io.github.guiboava.tibiajesterapi.controller.dto.request.UserRequestDTO;
import io.github.guiboava.tibiajesterapi.controller.dto.response.BossResponseDTO;
import io.github.guiboava.tibiajesterapi.controller.dto.response.UserResponseDTO;
import io.github.guiboava.tibiajesterapi.controller.mappers.UserMapper;
import io.github.guiboava.tibiajesterapi.entity.enums.UserType;
import io.github.guiboava.tibiajesterapi.entity.model.Boss;
import io.github.guiboava.tibiajesterapi.entity.model.User;
import io.github.guiboava.tibiajesterapi.exception.ResourceNotFoundException;
import io.github.guiboava.tibiajesterapi.repository.UserRepository;
import io.github.guiboava.tibiajesterapi.validator.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserValidator userValidator;
    private final PasswordEncoder encoder;

    private final BossService bossService;

    public UUID save(UserRequestDTO dto) {

        User user = userMapper.toEntity(dto);

        userValidator.validate(user);
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user).getId();

    }

    public void update(User user,
                       UserRequestDTO dto) {

        userMapper.updateEntityFromDto(dto, user);

        userValidator.validate(user);

        if (dto.password() != null && !dto.password().isBlank()) {
            user.setPassword((dto.password()));
        }

        userRepository.save(user);
    }

    public void delete(UUID userId) {

        User user = getEntityById(userId);
        userRepository.delete(user);

    }

    public UserResponseDTO getById(UUID userId) {

        return userRepository.findById(userId)
                .map(userMapper::toDTO)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Usuário não encontrado."
                        )
                );

    }

    public UserResponseDTO getAuthenticatedUser(User user) {

        return userMapper.toDTO(user);

    }

    public List<UserResponseDTO> searchByExample(String name,
                                                 String login,
                                                 String email,
                                                 UserType userType) {

        var user = new User();
        user.setName(name);
        user.setLogin(login);
        user.setEmail(email);
        user.setUserType(userType);

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreNullValues()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example<User> userExample = Example.of(user, matcher);

        return userRepository.findAll(userExample)
                .stream()
                .map(userMapper::toDTO)
                .toList();

    }

    @Transactional
    public void addBossesToAlarm(User user,
                                 BossAlarmRequestDTO dto) {

        Set<Boss> bosses = dto.bossIds()
                .stream()
                .map(bossService::getEntityById)
                .collect(Collectors.toSet());

        user.setBosses(bosses);

        userRepository.save(user);

    }

    @Transactional(readOnly = true)
    public Set<BossResponseDTO> getBossesAlarmFromUser(UUID userId) {

        User user = getEntityById(userId);

        return user
                .getBosses()
                .stream()
                .map(boss ->
                        bossService.getById(boss.getId()))
                .collect(Collectors.toSet());
    }

    public User getByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    public User getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User getByLoginByEmail(String login) {
        return userRepository.getByEmail(login);
    }

    protected User getEntityById(UUID worldId) {
        return userRepository.findById(worldId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Usuário não encontrado."
                        )
                );
    }

}
