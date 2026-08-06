package io.github.guiboava.tibiajesterapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.github.guiboava.tibiajesterapi.entity.enums.Party;
import io.github.guiboava.tibiajesterapi.entity.enums.Vocation;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "hunt_stats")
@Data
@EqualsAndHashCode(of = "id")
@EntityListeners(AuditingEntityListener.class)
public class HuntStats {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(name = "vocation", nullable = false)
    private Vocation vocation;

    @Enumerated(EnumType.STRING)
    @Column(name = "party", nullable = false)
    private Party party;

    @Column(name = "raw_xp", nullable = false)
    private Long rawXp;

    @Column(name = "xp_per_hour", nullable = false)
    private Long xpPerHour;

    @CreatedDate
    @Column(name = "created_date", nullable = false, updatable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column(name = "updated_date", nullable = false)
    private LocalDateTime updatedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hunt_id")
    @JsonIgnore
    private Hunt hunt;

}
