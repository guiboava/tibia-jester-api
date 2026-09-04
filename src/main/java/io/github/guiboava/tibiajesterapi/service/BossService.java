package io.github.guiboava.tibiajesterapi.service;

import io.github.guiboava.tibiajesterapi.controller.dto.BossRequestDTO;
import io.github.guiboava.tibiajesterapi.controller.dto.BossResponseDTO;
import io.github.guiboava.tibiajesterapi.controller.dto.ImageRequestDTO;
import io.github.guiboava.tibiajesterapi.controller.mappers.BossMapper;
import io.github.guiboava.tibiajesterapi.entity.model.Boss;
import io.github.guiboava.tibiajesterapi.exception.ResourceNotFoundException;
import io.github.guiboava.tibiajesterapi.repository.BossRepository;
import io.github.guiboava.tibiajesterapi.util.ImageURLBuilderUtils;
import io.github.guiboava.tibiajesterapi.validator.BossValidator;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BossService {

    private final BossRepository bossRepository;
    private final BossMapper bossMapper;
    private final BossValidator bossValidator;
    private final ImageService imageService;

    @Transactional
    public UUID save(BossRequestDTO bossDto, ImageRequestDTO imageDto) {

        Boss boss = bossMapper.toEntity(bossDto);
        boss.setImage(imageService.saveEntity(imageDto));

        bossValidator.validate(boss);

        return bossRepository.save(boss).getId();
    }

    @Transactional
    public void update(UUID bossId, @Valid BossRequestDTO bossDto, @Valid ImageRequestDTO imageDto) {

        Boss boss = getEntityById(bossId);

        imageService.update(boss.getImage().getId(), imageDto);

        bossMapper.updateEntityFromDto(bossDto, boss);
        bossValidator.validate(boss);
        bossRepository.save(boss);

    }

    @Transactional
    public void delete(UUID bossId) {

        Boss boss = getEntityById(bossId);

        imageService.delete(boss.getImage().getId());

        bossRepository.delete(boss);

    }

    public BossResponseDTO getById(UUID bossId) {

        return bossRepository.findById(bossId)
                .map(boss -> {
                    var url = ImageURLBuilderUtils.buildImageURL(boss.getImage());

                    return bossMapper.toDTO(boss, url);
                })
                .orElseThrow(() ->
                        new ResourceNotFoundException("Não foi encontrado nenhum dado de boss.")
                );


    }

    public Set<BossResponseDTO> getAll() {

        List<Boss> bosses = bossRepository.findAll();

        return bosses.stream()
                .map(boss -> {
                    var url = ImageURLBuilderUtils.buildImageURL(boss.getImage());
                    return bossMapper.toDTO(boss, url);
                }).collect(Collectors.toSet());

    }

    private Boss getEntityById(UUID bossId) {
        return bossRepository.findById(bossId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Boss não encontrado: " + bossId
                ));
    }
}
