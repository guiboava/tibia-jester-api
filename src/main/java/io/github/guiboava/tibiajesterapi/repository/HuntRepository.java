package io.github.guiboava.tibiajesterapi.repository;

import io.github.guiboava.tibiajesterapi.entity.model.Hunt;
import io.github.guiboava.tibiajesterapi.entity.model.Image;
import io.github.guiboava.tibiajesterapi.entity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HuntRepository extends JpaRepository<Hunt, UUID> {

    Hunt getById(UUID id);

}
