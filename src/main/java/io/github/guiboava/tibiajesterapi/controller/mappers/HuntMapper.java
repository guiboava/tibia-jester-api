package io.github.guiboava.tibiajesterapi.controller.mappers;

import io.github.guiboava.tibiajesterapi.controller.dto.HuntRequestDTO;
import io.github.guiboava.tibiajesterapi.controller.dto.HuntResponseDTO;
import io.github.guiboava.tibiajesterapi.controller.dto.UserRequestDTO;
import io.github.guiboava.tibiajesterapi.entity.model.Hunt;
import jakarta.validation.Valid;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public abstract class HuntMapper {

    public abstract Hunt toEntity(UserRequestDTO dto);

    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "updatedDate", ignore = true)
    public abstract void updateEntityFromDto(@Valid HuntRequestDTO dto, @MappingTarget Hunt hunt);

    public abstract HuntResponseDTO toDTO(Hunt hunt);

    public abstract HuntRequestDTO toRequestDTO(Hunt hunt);
}
