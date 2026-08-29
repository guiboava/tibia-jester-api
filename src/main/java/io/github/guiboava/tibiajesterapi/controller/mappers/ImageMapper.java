

package io.github.guiboava.tibiajesterapi.controller.mappers;

import io.github.guiboava.tibiajesterapi.controller.dto.ImageRequestDTO;
import io.github.guiboava.tibiajesterapi.controller.dto.ImageResponseDTO;
import io.github.guiboava.tibiajesterapi.entity.enums.ImageExtension;
import io.github.guiboava.tibiajesterapi.entity.model.Image;
import jakarta.validation.Valid;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UncheckedIOException;

@Mapper(componentModel = "spring", imports = {ImageExtension.class, MediaType.class})
public abstract class ImageMapper {

    @Mapping(target = "name", expression = "java(dto.file().getOriginalFilename())")
    @Mapping(target = "size", expression = "java(dto.file().getSize())")
    @Mapping(target = "extension", expression = "java(mapExtension(dto.file()))")
    @Mapping(target = "file", source = "file")
    public abstract Image toEntity(ImageRequestDTO dto);

    @Mapping(target = "name", expression = "java(dto.file().getOriginalFilename())")
    @Mapping(target = "size", expression = "java(dto.file().getSize())")
    @Mapping(target = "extension", expression = "java(mapExtension(dto.file()))")
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "updatedDate", ignore = true)
    public abstract void updateEntityFromDto(@Valid ImageRequestDTO dto, @MappingTarget Image image);

    public abstract ImageResponseDTO toDTO(Image image, String url);

    protected byte[] mapFile(MultipartFile file) {
        try {
            return file.getBytes();
        } catch (IOException e) {
            throw new UncheckedIOException("Erro ao ler a imagem.", e);
        }
    }

    protected ImageExtension mapExtension(MultipartFile file) {
        return switch (file.getContentType()) {
            case MediaType.IMAGE_JPEG_VALUE -> ImageExtension.JPEG;
            case MediaType.IMAGE_PNG_VALUE -> ImageExtension.PNG;
            case MediaType.IMAGE_GIF_VALUE -> ImageExtension.GIF;
            default -> throw new IllegalArgumentException(
                    "Formato de imagem não suportado: "
                            + file.getContentType()
            );
        };
    }

}
