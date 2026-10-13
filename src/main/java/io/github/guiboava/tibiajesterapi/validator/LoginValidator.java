package io.github.guiboava.tibiajesterapi.validator;


import io.github.guiboava.tibiajesterapi.controller.dto.request.LoginRequestDTO;
import io.github.guiboava.tibiajesterapi.entity.model.User;
import io.github.guiboava.tibiajesterapi.exception.InvalidCredentialsException;
import io.github.guiboava.tibiajesterapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LoginValidator {

    private final UserService userService;
    private final PasswordEncoder encoder;

    public User validate(LoginRequestDTO dto) {

        User user = userService.getByLogin(dto.username());

        if (user == null) {
            user = userService.getByLoginByEmail(dto.username());
        }

        if (user == null) {
            throw new InvalidCredentialsException("Usuário e/ou senha incorretos.");
        }

        if (!encoder.matches(dto.password(), user.getPassword())) {
            throw new InvalidCredentialsException("Usuário e/ou senha incorretos.");
        }

        return user;

    }

}
