package io.github.guiboava.tibiajesterapi.controller.mappers;


import io.github.guiboava.tibiajesterapi.controller.dto.request.AccountInformationRequestDTO;
import io.github.guiboava.tibiajesterapi.controller.dto.response.AccountInformationResponseDTO;
import io.github.guiboava.tibiajesterapi.entity.model.AccountInformation;
import jakarta.validation.Valid;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public abstract class AccountInformationMapper {

    public abstract AccountInformation toEntity(AccountInformationRequestDTO dto);

    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "updatedDate", ignore = true)
    public abstract void updateEntityFromDto(@Valid AccountInformationRequestDTO dto, @MappingTarget AccountInformation accountInformation);

    public abstract AccountInformationResponseDTO toDTO(AccountInformation accountInformation);

    public abstract AccountInformationRequestDTO toRequestDTO(AccountInformation accountInformation);

}
