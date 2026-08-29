package io.github.guiboava.tibiajesterapi.repository;

import io.github.guiboava.tibiajesterapi.entity.enums.ImageExtension;
import io.github.guiboava.tibiajesterapi.entity.model.Image;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.data.jpa.domain.Specification.allOf;
import static org.springframework.data.jpa.domain.Specification.anyOf;

public interface ImageRepository extends JpaRepository<Image, UUID>, JpaSpecificationExecutor<Image> {


    Image save(Image image);

    Image getById(UUID id);
}
