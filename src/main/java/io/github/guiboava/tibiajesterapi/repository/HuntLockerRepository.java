package io.github.guiboava.tibiajesterapi.repository;

import io.github.guiboava.tibiajesterapi.entity.model.Hunt;
import io.github.guiboava.tibiajesterapi.entity.model.HuntLocker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HuntLockerRepository extends JpaRepository<HuntLocker, UUID> {
}
