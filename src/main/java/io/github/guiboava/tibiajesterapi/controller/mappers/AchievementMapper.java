package io.github.guiboava.tibiajesterapi.controller.mappers;


import io.github.guiboava.tibiajesterapi.controller.dto.request.AchievementRequestDTO;
import io.github.guiboava.tibiajesterapi.controller.dto.response.AchievementResponseDTO;
import io.github.guiboava.tibiajesterapi.entity.model.Achievement;
import jakarta.validation.Valid;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public abstract class AchievementMapper {

    public abstract Achievement toEntity(AchievementRequestDTO dto);

    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "updatedDate", ignore = true)
    public abstract void updateEntityFromDto(@Valid AchievementRequestDTO dto, @MappingTarget Achievement achievement);

    public abstract AchievementResponseDTO toDTO(Achievement achievement);

    public abstract AchievementRequestDTO toRequestDTO(Achievement achievement);

}
