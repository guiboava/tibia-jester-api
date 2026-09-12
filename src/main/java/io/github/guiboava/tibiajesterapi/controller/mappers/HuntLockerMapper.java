package io.github.guiboava.tibiajesterapi.controller.mappers;


import io.github.guiboava.tibiajesterapi.controller.dto.request.HuntLockerRequestDTO;
import io.github.guiboava.tibiajesterapi.controller.dto.response.HuntLockerResponseDTO;
import io.github.guiboava.tibiajesterapi.controller.dto.response.HuntResponseDTO;
import io.github.guiboava.tibiajesterapi.controller.dto.response.WorldResponseDTO;
import io.github.guiboava.tibiajesterapi.entity.model.HuntLocker;
import jakarta.validation.Valid;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URI;

@Mapper(componentModel = "spring")
public abstract class HuntLockerMapper {

    @Autowired
    private HuntMapper huntMapper;
    @Autowired
    private WorldMapper worldMapper;

    public abstract HuntLocker toEntity(HuntLockerRequestDTO dto);

    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "updatedDate", ignore = true)
    public abstract void updateEntityFromDto(@Valid HuntLockerRequestDTO dto, @MappingTarget HuntLocker huntLocker);


    public HuntLockerResponseDTO toDTO(HuntLocker huntLocker, URI imageUrl) {
        HuntResponseDTO huntDTO = huntMapper.toDTO(huntLocker.getHunt(), imageUrl);
        WorldResponseDTO worldDTO = worldMapper.toDTO(huntLocker.getWorld());

        return new HuntLockerResponseDTO(
                huntLocker.getId(),
                huntLocker.getStatus(),
                huntLocker.getLockedAt(),
                huntDTO,
                worldDTO,
                huntLocker.getCreatedDate(),
                huntLocker.getUpdatedDate()
        );
    }


    public abstract HuntLockerRequestDTO toRequestDTO(HuntLocker huntLocker);

}