package io.github.guiboava.tibiajesterapi.controller.mappers;

import io.github.guiboava.tibiajesterapi.controller.dto.request.ClientRequestDTO;
import io.github.guiboava.tibiajesterapi.entity.model.Client;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class ClientMapper {

    public abstract Client toEntity(ClientRequestDTO dto);

    public abstract ClientRequestDTO toDTO(Client client);

}
