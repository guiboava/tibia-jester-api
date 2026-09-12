package io.github.guiboava.tibiajesterapi.service;

import io.github.guiboava.tibiajesterapi.controller.dto.request.GuildRequestDTO;
import io.github.guiboava.tibiajesterapi.controller.dto.response.GuildResponseDTO;
import io.github.guiboava.tibiajesterapi.controller.mappers.GuildMapper;
import io.github.guiboava.tibiajesterapi.entity.model.Guild;
import io.github.guiboava.tibiajesterapi.exception.ResourceNotFoundException;
import io.github.guiboava.tibiajesterapi.repository.GuildRepository;
import io.github.guiboava.tibiajesterapi.validator.GuildValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GuildService {

    private final GuildRepository guildRepository;
    private final GuildMapper guildMapper;
    private final GuildValidator guildValidator;

    public UUID save(GuildRequestDTO dto) {

        Guild guild = guildMapper.toEntity(dto);

        guildValidator.validate(guild);
        return guildRepository.save(guild).getId();

    }

    public void update(UUID guildId, GuildRequestDTO dto) {

        Guild guild = getEntityById(guildId);
        guildMapper.updateEntityFromDto(dto, guild);
        guildValidator.validate(guild);
        guildRepository.save(guild);

    }

    public void delete(UUID guildId) {

        Guild guild = getEntityById(guildId);
        guildRepository.delete(guild);

    }

    public GuildResponseDTO getById(UUID guildId) {

        return guildMapper.toDTO(getEntityById(guildId));

    }

    public Set<GuildResponseDTO> getAll() {

        return guildRepository.findAll().stream().map(guildMapper::toDTO).collect(Collectors.toSet());

    }

    protected Guild getEntityById(UUID guildId) {

        return guildRepository.findById(guildId).orElseThrow(() -> new ResourceNotFoundException("Mundo não encontrado para o id " + guildId));

    }

}
