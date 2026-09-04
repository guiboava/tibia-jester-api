package io.github.guiboava.tibiajesterapi.controller.mappers;

import io.github.guiboava.tibiajesterapi.controller.dto.HuntStatusRequestDTO;
import io.github.guiboava.tibiajesterapi.controller.dto.HuntStatusResponseDTO;
import io.github.guiboava.tibiajesterapi.entity.model.HuntStatus;
import jakarta.validation.Valid;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public abstract class HuntStatusMapper {


    public abstract HuntStatus toEntity(HuntStatusRequestDTO dto);

    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "updatedDate", ignore = true)
    public abstract void updateEntityFromDto(@Valid HuntStatusRequestDTO dto, @MappingTarget HuntStatus huntStatus);

    @Mapping(target = "huntId", source = "hunt.id")
    public abstract HuntStatusResponseDTO toDTO(HuntStatus huntStatus);

    public abstract HuntStatusRequestDTO toRequestDTO(HuntStatus huntStatus);
}
