package io.github.guiboava.tibiajesterapi.repository;

import io.github.guiboava.tibiajesterapi.entity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
}
