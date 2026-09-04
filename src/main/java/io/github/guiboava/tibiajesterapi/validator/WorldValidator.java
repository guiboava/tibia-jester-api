package io.github.guiboava.tibiajesterapi.validator;

import io.github.guiboava.tibiajesterapi.entity.model.World;
import io.github.guiboava.tibiajesterapi.repository.WorldRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WorldValidator {

    private final WorldRepository worldRepository;

    public void validate(World world) {


    }


}
