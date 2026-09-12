package io.github.guiboava.tibiajesterapi.validator;

import io.github.guiboava.tibiajesterapi.entity.model.HuntLocker;
import io.github.guiboava.tibiajesterapi.repository.HuntLockerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HuntLockerValidator {

    private final HuntLockerRepository huntLockerRepository;

    public void validate(HuntLocker huntLocker) {


    }


}
