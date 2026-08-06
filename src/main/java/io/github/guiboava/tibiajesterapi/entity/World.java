package io.github.guiboava.tibiajesterapi.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "worlds")
@Data
@EqualsAndHashCode(of = "id")
@EntityListeners(AuditingEntityListener.class)
public class World {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Length(max = 100)
    @Column(name = "name", nullable = false)
    private String name;

    @Length(max = 20)
    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "players_online")
    private Integer playersOnline;

    @Length(max = 50)
    @Column(name = "pvp_type")
    private String pvpType;

    @Column(name = "premium_only")
    private Boolean premiumOnly;

    @Length(max = 30)
    @Column(name = "transfer_type")
    private String transferType;

    @Column(name = "battleye_protected")
    private Boolean battleyeProtected;

    @Column(name = "battleye_date")
    private LocalDate battleyeDate;

    @Length(max = 30)
    @Column(name = "game_world_type")
    private String gameWorldType;

    @OneToMany(mappedBy = "world")
    private Set<Character> characters = new HashSet<>();

    @CreatedDate
    @Column(name = "created_date", nullable = false, updatable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column(name = "updated_date", nullable = false)
    private LocalDateTime updatedDate;

}