package io.github.guiboava.tibiajesterapi.controller.mappers;

import io.github.guiboava.tibiajesterapi.controller.dto.UserRequestDTO;
import io.github.guiboava.tibiajesterapi.controller.dto.UserResponseDTO;
import io.github.guiboava.tibiajesterapi.entity.model.User;
import jakarta.validation.Valid;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public abstract class UserMapper {

    public abstract User toEntity(UserRequestDTO dto);

    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "updatedDate", ignore = true)
    public abstract void updateEntityFromDto(@Valid UserRequestDTO dto, @MappingTarget User user);

    public abstract UserResponseDTO toDTO(User user);

    @Mapping(target = "confirmPassword", expression = "java(user.getPassword())")
    public abstract UserRequestDTO toRequestDTO(User user);

}
