package io.github.guiboava.tibiajesterapi.controller.mappers;


import io.github.guiboava.tibiajesterapi.controller.dto.request.UserCharacterRequestDTO;
import io.github.guiboava.tibiajesterapi.controller.dto.response.UserCharacterResponseDTO;
import io.github.guiboava.tibiajesterapi.entity.model.UserCharacter;
import jakarta.validation.Valid;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.Objects;
import java.util.Optional;

@Mapper(componentModel = "spring")
public abstract class UserCharacterMapper {

    public abstract UserCharacter toEntity(UserCharacterRequestDTO dto);

    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "updatedDate", ignore = true)
    public abstract void updateEntityFromDto(@Valid UserCharacterRequestDTO dto, @MappingTarget UserCharacter userCharacter);

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "characterId", source = "character.id")
    public abstract UserCharacterResponseDTO toDTO(UserCharacter userCharacter);

    public abstract UserCharacterRequestDTO toRequestDTO(UserCharacter userCharacter);

}
