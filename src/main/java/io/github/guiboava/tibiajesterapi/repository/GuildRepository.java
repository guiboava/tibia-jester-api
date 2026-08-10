package io.github.guiboava.tibiajesterapi.repository;

import io.github.guiboava.tibiajesterapi.entity.model.Boss;
import io.github.guiboava.tibiajesterapi.entity.model.Guild;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface GuildRepository extends JpaRepository<Guild, UUID> {
}
