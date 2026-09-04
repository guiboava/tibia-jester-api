package io.github.guiboava.tibiajesterapi.controller.mappers;

import io.github.guiboava.tibiajesterapi.controller.dto.UserRequestDTO;
import io.github.guiboava.tibiajesterapi.controller.dto.WorldRequestDTO;
import io.github.guiboava.tibiajesterapi.controller.dto.WorldResponseDTO;
import io.github.guiboava.tibiajesterapi.entity.model.User;
import io.github.guiboava.tibiajesterapi.entity.model.World;
import jakarta.validation.Valid;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public abstract class WorldMapper {

    public abstract World toEntity(WorldRequestDTO dto);

    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "updatedDate", ignore = true)
    public abstract void updateEntityFromDto(@Valid WorldRequestDTO dto, @MappingTarget World world);

    public abstract WorldResponseDTO toDTO(World world);

    public abstract WorldRequestDTO toRequestDTO(World world);

}
