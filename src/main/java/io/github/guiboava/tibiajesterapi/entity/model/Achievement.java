package io.github.guiboava.tibiajesterapi.entity.model;

import io.github.guiboava.tibiajesterapi.entity.enums.Grade;
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
@Table(name = "achievements")
@Data
@EqualsAndHashCode(of = "id")
@EntityListeners(AuditingEntityListener.class)
public class Achievement {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "grade", nullable = false)
    private Grade grade;

    @Column(name = "secret", nullable = false)
    private Boolean secret;

    @ManyToMany(mappedBy = "achievements")
    private Set<Character> characters = new HashSet<>();

    @CreatedDate
    @Column(name = "created_date", nullable = false, updatable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column(name = "updated_date", nullable = false)
    private LocalDateTime updatedDate;

}
