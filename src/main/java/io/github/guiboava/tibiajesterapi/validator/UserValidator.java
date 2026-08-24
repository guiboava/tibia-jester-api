package io.github.guiboava.tibiajesterapi.validator;

import io.github.guiboava.tibiajesterapi.entity.model.User;
import io.github.guiboava.tibiajesterapi.exception.DuplicateRegisterException;
import io.github.guiboava.tibiajesterapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserValidator {

    private final UserRepository userRepository;

    public void validate(User user) {

        duplicateRegister(user);

    }

    private void duplicateRegister(User user) {
        if (user.getId() == null) {
            if (userRepository.existsByLogin(user.getLogin())) {
                throw new DuplicateRegisterException("Já existe um usuário com este nome de usuário.");
            }
            if (userRepository.existsByEmail(user.getEmail())) {
                throw new DuplicateRegisterException("Já existe um usuário com este email.");
            }
        } else {
            if (userRepository.existsByLoginAndIdNot(user.getLogin(), user.getId())) {
                throw new DuplicateRegisterException("Já existe um usuário com este nome de usuário.");
            }
            if (userRepository.existsByEmailAndIdNot(user.getEmail(), user.getId())) {
                throw new DuplicateRegisterException("Já existe um usuário com este email.");
            }
        }
    }


}
