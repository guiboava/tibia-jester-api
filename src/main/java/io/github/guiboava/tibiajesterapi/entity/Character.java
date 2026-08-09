package io.github.guiboava.tibiajesterapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.github.guiboava.tibiajesterapi.entity.enums.AccountStatus;
import io.github.guiboava.tibiajesterapi.entity.enums.Gender;
import io.github.guiboava.tibiajesterapi.entity.enums.Vocation;
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
@Table(name = "characters")
@Data
@EqualsAndHashCode(of = "id")
@EntityListeners(AuditingEntityListener.class)
public class Character {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "name", nullable = false, unique = true, length = 100)
    private String name;

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "unlocked_titles", nullable = false)
    private Long unlockedTitles;

    @Column(name = "vocation", nullable = false)
    @Enumerated(EnumType.STRING)
    private Vocation vocation;

    @Column(name = "level",nullable = false)
    private Integer level;

    @Column(name = "achievement_points",nullable = false)
    private Integer achievementPoints;

    @Column(name = "residence", nullable = false)
    private String residence;

    @Column(name = "married_to", nullable = false)
    private String marriedTo;

    @Column(name = "last_login", nullable = false)
    private LocalDateTime lastLogin;

    @Column(name = "account_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;

    @Column(name = "comment", nullable = false)
    private String comment;

    @Column(name = "rank", nullable = false)
    private String rank;

    @ManyToOne
    @JoinColumn(name = "world_id")
    private World world;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_information_id")
    private AccountInformation accountInformation;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "character_achievement", joinColumns = @JoinColumn(name = "character_id"), inverseJoinColumns = @JoinColumn(name = "achievement_id"))
    private Set<Achievement> achievements = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "guild_id")
    private Guild guild;

    @OneToMany(mappedBy = "character", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<UserCharacter> userCharacters = new HashSet<>();

    @CreatedDate
    @Column(name = "created_date", nullable = false, updatable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column(name = "updated_date", nullable = false)
    private LocalDateTime updatedDate;
}