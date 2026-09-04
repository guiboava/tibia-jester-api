package io.github.guiboava.tibiajesterapi.controller.mappers;

import io.github.guiboava.tibiajesterapi.controller.dto.BossRequestDTO;
import io.github.guiboava.tibiajesterapi.controller.dto.BossResponseDTO;
import io.github.guiboava.tibiajesterapi.controller.dto.ImageResponseDTO;
import io.github.guiboava.tibiajesterapi.entity.model.Boss;
import jakarta.validation.Valid;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URI;

@Mapper(componentModel = "spring")
public abstract class BossMapper {

    @Autowired
    private ImageMapper imageMapper;

    public abstract Boss toEntity(BossRequestDTO dto);

    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "updatedDate", ignore = true)
    public abstract void updateEntityFromDto(@Valid BossRequestDTO dto, @MappingTarget Boss boss);

    public BossResponseDTO toDTO(Boss boss, URI imageUrl) {
        ImageResponseDTO imageDTO = imageMapper.toDTO(boss.getImage(), imageUrl.toString());

        return new BossResponseDTO(
                boss.getId(),
                boss.getName(),
                imageDTO
        );
    }


    public abstract BossRequestDTO toRequestDTO(Boss boss);
}
