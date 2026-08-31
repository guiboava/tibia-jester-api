package io.github.guiboava.tibiajesterapi.service;

import io.github.guiboava.tibiajesterapi.controller.dto.HuntRequestDTO;
import io.github.guiboava.tibiajesterapi.controller.dto.HuntResponseDTO;
import io.github.guiboava.tibiajesterapi.controller.dto.ImageRequestDTO;
import io.github.guiboava.tibiajesterapi.controller.mappers.HuntMapper;
import io.github.guiboava.tibiajesterapi.entity.model.Hunt;
import io.github.guiboava.tibiajesterapi.exception.ResourceNotFoundException;
import io.github.guiboava.tibiajesterapi.repository.HuntRepository;
import io.github.guiboava.tibiajesterapi.util.ImageURLBuilderUtils;
import io.github.guiboava.tibiajesterapi.validator.HuntValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HuntService {

    private final HuntRepository huntRepository;
    private final HuntMapper huntMapper;
    private final HuntValidator huntValidator;
    private final ImageService imageService;

    @Transactional
    public UUID save(HuntRequestDTO huntDto, ImageRequestDTO imageDto) {

        Hunt hunt = huntMapper.toEntity(huntDto);
        hunt.setImage(imageService.saveEntity(imageDto));

        huntValidator.validate(hunt);

        return huntRepository.save(hunt).getId();
    }

    @Transactional
    public void update(UUID huntId, @Valid HuntRequestDTO huntDto, @Valid ImageRequestDTO imageDto) {

        var hunt = huntRepository.getById(huntId);

        imageService.update(hunt.getImage().getId(), imageDto);

        huntMapper.updateEntityFromDto(huntDto, hunt);
        huntValidator.validate(hunt);
        huntRepository.save(hunt);

    }

    @Transactional
    public void delete(UUID huntId) {

        Hunt hunt = huntRepository.getById(huntId);

        imageService.delete(hunt.getImage().getId());

        huntRepository.delete(hunt);

    }

    public HuntResponseDTO getById(UUID huntId) {

        return huntRepository.findById(huntId)
                .map(hunt -> {
                    var url = ImageURLBuilderUtils.buildImageURL(hunt.getImage());

                    return huntMapper.toDTO(hunt, url);
                })
                .orElseThrow(() ->
                        new ResourceNotFoundException("Não foi encontrado nenhum dado de hunt.")
                );


    }

    public @Nullable Set<HuntResponseDTO> getAll() {

        List<Hunt> hunts = huntRepository.findAll();

        return hunts.stream()
                .map(hunt -> {
                    var url = ImageURLBuilderUtils.buildImageURL(hunt.getImage());
                    return huntMapper.toDTO(hunt, url);
                }).collect(Collectors.toSet());

    }
}
