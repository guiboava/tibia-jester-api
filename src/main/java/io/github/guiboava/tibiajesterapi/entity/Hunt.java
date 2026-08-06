package io.github.guiboava.tibiajesterapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.github.guiboava.tibiajesterapi.entity.enums.ImageExtension;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "hunts")
@Data
@EqualsAndHashCode(of = "id")
@EntityListeners(AuditingEntityListener.class)
public class Hunt {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "level_min", nullable = false)
    private Integer levelMin;

    @Column(name = "level_max", nullable = false)
    private Integer levelMax;

    @Column(name = "image", nullable = false)
    @Lob
    private byte[] image;

    @Column(name = "image_extension", nullable = false)
    @Enumerated(EnumType.STRING)
    private ImageExtension imageExtension;

    public String getFileName() {
        return name + "." + imageExtension.name().toLowerCase();
    }

    @CreatedDate
    @Column(name = "created_date", nullable = false, updatable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column(name = "updated_date", nullable = false)
    private LocalDateTime updatedDate;

    @OneToMany(mappedBy = "hunt", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<HuntStats> huntStatsList = new HashSet<>();

}
