package io.github.guiboava.tibiajesterapi.validator;

import io.github.guiboava.tibiajesterapi.entity.model.Guild;
import io.github.guiboava.tibiajesterapi.repository.GuildRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GuildValidator {

    private final GuildRepository guildRepository;

    public void validate(Guild guild) {


    }


}
