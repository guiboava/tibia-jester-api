package io.github.guiboava.tibiajesterapi.entity.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(
        name = "hunt_lockers",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"hunt_id", "world_id"}
        )
)
@EqualsAndHashCode(of = "id")
@EntityListeners(AuditingEntityListener.class)
public class HuntLocker {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "status", nullable = false)
    private Boolean status;

    @Column(name = "hunt_time", nullable = false)
    private LocalDateTime lockedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hunt_id", nullable = false)
    private Hunt hunt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "world_id", nullable = false)
    private World world;

    @CreatedDate
    @Column(name = "created_date", nullable = false, updatable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column(name = "updated_date", nullable = false)
    private LocalDateTime updatedDate;

}
