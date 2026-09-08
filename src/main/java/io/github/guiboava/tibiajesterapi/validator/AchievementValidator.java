package io.github.guiboava.tibiajesterapi.validator;

import io.github.guiboava.tibiajesterapi.entity.model.Achievement;
import io.github.guiboava.tibiajesterapi.repository.AchievementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AchievementValidator {

    private final AchievementRepository achievementRepository;

    public void validate(Achievement achievement) {


    }


}
