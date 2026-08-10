package io.github.guiboava.tibiajesterapi.repository;

import io.github.guiboava.tibiajesterapi.entity.model.User;
import io.github.guiboava.tibiajesterapi.entity.model.World;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface WorldRepository extends JpaRepository<World, UUID> {
}
