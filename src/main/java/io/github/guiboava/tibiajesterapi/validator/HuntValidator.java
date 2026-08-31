package io.github.guiboava.tibiajesterapi.validator;

import io.github.guiboava.tibiajesterapi.entity.model.Hunt;
import io.github.guiboava.tibiajesterapi.repository.HuntRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HuntValidator {

    private final HuntRepository huntRepository;

    public void validate(Hunt hunt) {


    }


}
