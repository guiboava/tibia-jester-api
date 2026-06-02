package io.github.guiboava.tibiajesterapi.entity;

import io.github.guiboava.tibiajesterapi.entity.enums.UserType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "users")
@Data
@EqualsAndHashCode(of = "id")
@EntityListeners(AuditingEntityListener.class)
public class User {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name", length = 500, nullable = false)
    private String name;

    @Column(name = "login", nullable = false, length = 500, unique = true)
    private String login;

    @Column(name = "email", nullable = false, length = 500, unique = true)
    private String email;

    @Column(name = "user_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserType userType;

    @Column(name = "password", nullable = false, length = 60)
    private String password;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @CreatedDate
    @Column(name = "created_date", nullable = false, insertable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column(name = "updated_date", nullable = false)
    private LocalDateTime updatedDate;

}
