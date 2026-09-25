package io.github.guiboava.tibiajesterapi.validator;

import io.github.guiboava.tibiajesterapi.entity.model.UserCharacter;
import io.github.guiboava.tibiajesterapi.repository.UserCharacterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserCharacterValidator {

    private final UserCharacterRepository userCharacterRepository;

    public void validate(UserCharacter userCharacter) {


    }


}
