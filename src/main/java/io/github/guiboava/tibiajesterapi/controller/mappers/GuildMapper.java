package io.github.guiboava.tibiajesterapi.controller.mappers;


import io.github.guiboava.tibiajesterapi.controller.dto.request.GuildRequestDTO;
import io.github.guiboava.tibiajesterapi.controller.dto.response.GuildResponseDTO;
import io.github.guiboava.tibiajesterapi.entity.model.Guild;
import jakarta.validation.Valid;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public abstract class GuildMapper {

    public abstract Guild toEntity(GuildRequestDTO dto);

    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "updatedDate", ignore = true)
    public abstract void updateEntityFromDto(@Valid GuildRequestDTO dto, @MappingTarget Guild guild);

    public abstract GuildResponseDTO toDTO(Guild guild);

    public abstract GuildRequestDTO toRequestDTO(Guild guild);

}
