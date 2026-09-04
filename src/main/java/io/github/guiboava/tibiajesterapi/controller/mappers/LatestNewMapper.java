package io.github.guiboava.tibiajesterapi.controller.mappers;

import io.github.guiboava.tibiajesterapi.controller.dto.LatestNewRequestDTO;
import io.github.guiboava.tibiajesterapi.controller.dto.LatestNewResponseDTO;
import io.github.guiboava.tibiajesterapi.entity.model.LatestNew;
import jakarta.validation.Valid;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public abstract class LatestNewMapper {

    public abstract LatestNew toEntity(LatestNewRequestDTO dto);

    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "updatedDate", ignore = true)
    public abstract void updateEntityFromDto(@Valid LatestNewRequestDTO dto, @MappingTarget LatestNew latestNew);

    public abstract LatestNewResponseDTO toDTO(LatestNew latestNew);

    public abstract LatestNewRequestDTO toRequestDTO(LatestNew latestNew);

}
