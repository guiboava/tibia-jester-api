package io.github.guiboava.tibiajesterapi.service;

import io.github.guiboava.tibiajesterapi.controller.dto.request.AchievementRequestDTO;
import io.github.guiboava.tibiajesterapi.controller.dto.response.AchievementResponseDTO;
import io.github.guiboava.tibiajesterapi.controller.mappers.AchievementMapper;
import io.github.guiboava.tibiajesterapi.entity.model.Achievement;
import io.github.guiboava.tibiajesterapi.exception.ResourceNotFoundException;
import io.github.guiboava.tibiajesterapi.repository.AchievementRepository;
import io.github.guiboava.tibiajesterapi.validator.AchievementValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AchievementService {

    private final AchievementRepository achievementRepository;
    private final AchievementMapper achievementMapper;
    private final AchievementValidator achievementValidator;

    public UUID save(AchievementRequestDTO dto) {

        Achievement achievement = achievementMapper.toEntity(dto);

        achievementValidator.validate(achievement);
        return achievementRepository.save(achievement).getId();

    }

    public void update(UUID achievementId, AchievementRequestDTO dto) {

        Achievement achievement = getEntityById(achievementId);
        achievementMapper.updateEntityFromDto(dto, achievement);
        achievementValidator.validate(achievement);
        achievementRepository.save(achievement);

    }

    public void delete(UUID achievementId) {

        Achievement achievement = getEntityById(achievementId);
        achievementRepository.delete(achievement);

    }

    public AchievementResponseDTO getById(UUID achievementId) {

        return achievementMapper.toDTO(getEntityById(achievementId));

    }

    public Set<AchievementResponseDTO> getAll() {

        return achievementRepository.findAll().stream().map(achievementMapper::toDTO).collect(Collectors.toSet());

    }

    protected Achievement getEntityById(UUID achievementId) {

        return achievementRepository.findById(achievementId).orElseThrow(() -> new ResourceNotFoundException("Achievement não encontrado para o id " + achievementId));

    }

}
