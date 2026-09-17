package io.github.guiboava.tibiajesterapi.validator;

import io.github.guiboava.tibiajesterapi.entity.model.AccountInformation;
import io.github.guiboava.tibiajesterapi.repository.AccountInformationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountInformationValidator {

    private final AccountInformationRepository accountInformationRepository;

    public void validate(AccountInformation accountInformation) {


    }


}
