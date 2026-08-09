package io.github.guiboava.tibiajesterapi.entity;


import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(
        name = "user_characters",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"user_id", "character_id"}
                )
        }
)
public class UserCharacter {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "character_id", nullable = false)
    private Character character;

    @Column(name = "is_main", nullable = false)
    private Boolean isMain;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
}