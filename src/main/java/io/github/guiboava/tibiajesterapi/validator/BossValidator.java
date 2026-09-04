package io.github.guiboava.tibiajesterapi.validator;

import io.github.guiboava.tibiajesterapi.entity.model.Boss;
import io.github.guiboava.tibiajesterapi.repository.BossRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BossValidator {

    private final BossRepository bossRepository;

    public void validate(Boss boss) {


    }


}
