package io.github.guiboava.tibiajesterapi.service;

import io.github.guiboava.tibiajesterapi.controller.dto.ImageRequestDTO;
import io.github.guiboava.tibiajesterapi.controller.dto.ImageResponseDTO;
import io.github.guiboava.tibiajesterapi.controller.mappers.ImageMapper;
import io.github.guiboava.tibiajesterapi.entity.model.Image;
import io.github.guiboava.tibiajesterapi.repository.ImageRepository;
import io.github.guiboava.tibiajesterapi.validator.ImageValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;
    private final ImageMapper imageMapper;
    private final ImageValidator imageValidator;


    @Transactional
    public UUID save(ImageRequestDTO dto) {

        Image image = imageMapper.toEntity(dto);
        return imageRepository.save(image).getId();

    }

    @Transactional
    public void delete(UUID imageId) {

        Image image = imageRepository.getById(imageId);
        imageValidator.validate(image);
        imageRepository.delete(image);


    }

    @Transactional
    public void update(UUID imageId, ImageRequestDTO dto) {

        Image image = imageRepository.getById((imageId));

        imageMapper.updateEntityFromDto(dto, image);
        imageValidator.validate(image);
        imageRepository.save(image);

    }

    public ResponseEntity<byte[]> getById(UUID imageId) {

        var possibleImage = imageRepository.findById(imageId);
        if (possibleImage.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Image image = possibleImage.get();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(image.getExtension().getMediaType());
        headers.setContentLength(image.getSize());
        headers.setContentDispositionFormData("inline; filename=\"" + image.getFileName() + "\"", image.getFileName());

        return new ResponseEntity<>(image.getFile(), headers, HttpStatus.OK);

    }

    public Set<ImageResponseDTO> getAll() {

        List<Image> images = imageRepository.findAll();

        return images.stream().map(image -> {
            var url = buildImageURL(image);
            return imageMapper.toDTO(image,url.toString());
        }).collect(Collectors.toSet());



    }

    private URI buildImageURL(Image image) {
        return ServletUriComponentsBuilder
                .fromCurrentRequestUri().path("/" + image.getId()).build()
                .toUri();
    }

}
