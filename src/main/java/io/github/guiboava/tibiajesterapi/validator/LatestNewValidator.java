package io.github.guiboava.tibiajesterapi.validator;

import io.github.guiboava.tibiajesterapi.entity.model.LatestNew;
import io.github.guiboava.tibiajesterapi.repository.LatestNewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LatestNewValidator {

    private final LatestNewRepository latestNewRepository;

    public void validate(LatestNew latestNew) {


    }


}
