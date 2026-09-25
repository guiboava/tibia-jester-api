package io.github.guiboava.tibiajesterapi.service;

import io.github.guiboava.tibiajesterapi.controller.dto.request.HuntLockerRequestDTO;
import io.github.guiboava.tibiajesterapi.controller.dto.response.HuntLockerResponseDTO;
import io.github.guiboava.tibiajesterapi.controller.mappers.HuntLockerMapper;
import io.github.guiboava.tibiajesterapi.entity.model.Hunt;
import io.github.guiboava.tibiajesterapi.entity.model.HuntLocker;
import io.github.guiboava.tibiajesterapi.entity.model.World;
import io.github.guiboava.tibiajesterapi.exception.ResourceNotFoundException;
import io.github.guiboava.tibiajesterapi.repository.HuntLockerRepository;
import io.github.guiboava.tibiajesterapi.util.ImageURLBuilderUtils;
import io.github.guiboava.tibiajesterapi.validator.HuntLockerValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HuntLockerService {

    private final HuntLockerRepository huntLockerRepository;
    private final HuntLockerMapper huntLockerMapper;
    private final HuntLockerValidator huntLockerValidator;

    private final HuntService huntService;
    private final WorldService worldService;

    @Transactional
    public UUID save(HuntLockerRequestDTO dto) {

        HuntLocker huntLocker = huntLockerMapper.toEntity(dto);
        Hunt hunt = huntService.getEntityById(dto.huntId());
        World world = worldService.getEntityById(dto.worldId());

        huntLocker.setHunt(hunt);
        huntLocker.setWorld(world);

        huntLockerValidator.validate(huntLocker);
        return huntLockerRepository.save(huntLocker).getId();

    }

    @Transactional
    public void update(UUID huntLockerId, HuntLockerRequestDTO dto) {

        HuntLocker huntLocker = getEntityById(huntLockerId);
        huntLockerMapper.updateEntityFromDto(dto, huntLocker);

        Hunt hunt = huntService.getEntityById(dto.huntId());
        World world = worldService.getEntityById(dto.worldId());

        huntLocker.setHunt(hunt);
        huntLocker.setWorld(world);

        huntLockerValidator.validate(huntLocker);
        huntLockerRepository.save(huntLocker);

    }

    @Transactional
    public void delete(UUID huntLockerId) {

        HuntLocker huntLocker = getEntityById(huntLockerId);
        huntLockerRepository.delete(huntLocker);

    }

    @Transactional(readOnly = true)
    public HuntLockerResponseDTO getById(UUID huntLockerId) {

        return huntLockerRepository.findById(huntLockerId)
                .map(huntLocker -> {
                    var url = ImageURLBuilderUtils.buildImageURL(huntLocker.getHunt().getImage());
                    return huntLockerMapper.toDTO(huntLocker, url);
                })
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Locker de hunt não encontrado para o id: " + huntLockerId
                        )
                );

    }

    @Transactional(readOnly = true)
    public Set<HuntLockerResponseDTO> getAll() {

        return huntLockerRepository.findAll()
                .stream()
                .map(huntLocker -> {
                    var url = ImageURLBuilderUtils.buildImageURL(
                            huntLocker.getHunt().getImage()
                    );
                    return huntLockerMapper.toDTO(huntLocker, url);
                })
                .collect(Collectors.toSet());
    }

    protected HuntLocker getEntityById(UUID huntLockerId) {

        return huntLockerRepository.findById(huntLockerId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Locker de hunt não encontrado para o id " + huntLockerId
                        )
                );

    }

}
