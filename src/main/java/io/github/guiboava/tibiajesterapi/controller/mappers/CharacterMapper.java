package io.github.guiboava.tibiajesterapi.controller.mappers;


import io.github.guiboava.tibiajesterapi.controller.dto.request.CharacterRequestDTO;
import io.github.guiboava.tibiajesterapi.controller.dto.response.CharacterResponseDTO;
import io.github.guiboava.tibiajesterapi.entity.model.Character;
import jakarta.validation.Valid;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public abstract class CharacterMapper {

    public abstract Character toEntity(CharacterRequestDTO dto);

    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "updatedDate", ignore = true)
    public abstract void updateEntityFromDto(@Valid CharacterRequestDTO dto, @MappingTarget Character character);

    public abstract CharacterResponseDTO toDTO(Character character);

    public abstract CharacterRequestDTO toRequestDTO(Character character);

}
