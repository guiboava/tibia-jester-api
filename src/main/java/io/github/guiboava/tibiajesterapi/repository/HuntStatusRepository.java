package io.github.guiboava.tibiajesterapi.repository;

import io.github.guiboava.tibiajesterapi.entity.model.Guild;
import io.github.guiboava.tibiajesterapi.entity.model.HuntStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HuntStatusRepository extends JpaRepository<HuntStatus, UUID> {
}
