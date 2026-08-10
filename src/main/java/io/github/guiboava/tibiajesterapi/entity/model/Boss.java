package io.github.guiboava.tibiajesterapi.entity.model;

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
@Table(name = "bosses")
@Data
@EqualsAndHashCode(of = "id")
@EntityListeners(AuditingEntityListener.class)
public class Boss {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    private String name;

    @Column(name = "image", nullable = false)
    @Lob
    private byte[] image;

    @Column(name = "image_extension", nullable = false)
    @Enumerated(EnumType.STRING)
    private ImageExtension imageExtension;

    public String getFileName() {
        return name + "." + imageExtension.name().toLowerCase();
    }

    @OneToMany(mappedBy = "boss")
    private Set<UserBossAlarm> userBossAlarms = new HashSet<>();

    @CreatedDate
    @Column(name = "created_date", nullable = false, updatable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column(name = "updated_date", nullable = false)
    private LocalDateTime updatedDate;


}
