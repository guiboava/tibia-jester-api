package io.github.guiboava.tibiajesterapi.controller.mappers;

import io.github.guiboava.tibiajesterapi.controller.dto.request.BossRequestDTO;
import io.github.guiboava.tibiajesterapi.controller.dto.response.BossResponseDTO;
import io.github.guiboava.tibiajesterapi.entity.model.Boss;
import jakarta.validation.Valid;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = ImageMapper.class)
public abstract class BossMapper {

    @Autowired
    protected ImageMapper imageMapper;

    public abstract Boss toEntity(BossRequestDTO dto);

    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "updatedDate", ignore = true)
    public abstract void updateEntityFromDto(@Valid BossRequestDTO dto, @MappingTarget Boss boss);

    @Mapping(target = "image", expression = "java(imageMapper.toDTO(boss.getImage()))")
    public abstract BossResponseDTO toDTO(Boss boss);

    public abstract BossRequestDTO toRequestDTO(Boss boss);
}