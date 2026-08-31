package io.github.guiboava.tibiajesterapi.controller.mappers;

import io.github.guiboava.tibiajesterapi.controller.dto.HuntRequestDTO;
import io.github.guiboava.tibiajesterapi.controller.dto.HuntResponseDTO;
import io.github.guiboava.tibiajesterapi.controller.dto.ImageResponseDTO;
import io.github.guiboava.tibiajesterapi.controller.dto.UserRequestDTO;
import io.github.guiboava.tibiajesterapi.entity.model.Hunt;
import jakarta.validation.Valid;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URI;

@Mapper(componentModel = "spring")
public abstract class HuntMapper {

    @Autowired
    private ImageMapper imageMapper;

    public abstract Hunt toEntity(HuntRequestDTO dto);

    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "updatedDate", ignore = true)
    public abstract void updateEntityFromDto(@Valid HuntRequestDTO dto, @MappingTarget Hunt hunt);

    public HuntResponseDTO toDTO(Hunt hunt, URI imageUrl) {
        ImageResponseDTO imageDTO = imageMapper.toDTO(hunt.getImage(), imageUrl.toString());

        return new HuntResponseDTO(
                hunt.getId(),
                hunt.getName(),
                hunt.getLevelMin(),
                hunt.getLevelMax(),
                imageDTO
        );
    }


    public abstract HuntRequestDTO toRequestDTO(Hunt hunt);
}
