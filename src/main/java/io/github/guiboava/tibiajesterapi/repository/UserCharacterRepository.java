package io.github.guiboava.tibiajesterapi.repository;

import io.github.guiboava.tibiajesterapi.entity.model.Character;
import io.github.guiboava.tibiajesterapi.entity.model.User;
import io.github.guiboava.tibiajesterapi.entity.model.UserCharacter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface UserCharacterRepository extends JpaRepository<UserCharacter, UUID> {


    Optional<UserCharacter> findByUserAndCharacter(User user, Character character);

    Optional<Set<UserCharacter>> findAllByUser(User user);
}
