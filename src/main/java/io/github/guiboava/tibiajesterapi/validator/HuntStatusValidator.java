package io.github.guiboava.tibiajesterapi.validator;

import io.github.guiboava.tibiajesterapi.entity.model.HuntStatus;
import io.github.guiboava.tibiajesterapi.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class HuntStatusValidator {

    public void validate(HuntStatus huntStatus, UUID huntId) {

        belongsToHunt(huntStatus, huntId);

    }

    private void belongsToHunt(HuntStatus huntStatus, UUID huntId) {
        if (!huntStatus.getHunt().getId().equals(huntId)) {
            throw new EntityNotFoundException("Status não encontrado para esta hunt.");
        }
    }

}