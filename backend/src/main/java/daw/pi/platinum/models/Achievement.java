package daw.pi.platinum.models;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "achievements", indexes = {
    @Index(name = "idx_achievement_game_id", columnList = "game_id")
})
public class Achievement {

    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "achievement_id")
    private Long achievementId;

    @NotBlank(message = "Name is required")
    @Column(name = "name", nullable = false, columnDefinition = "TEXT")
    private String name;

    @NotBlank(message = "Display name is required")
    @Column(name = "displayName", nullable = false, columnDefinition = "TEXT")
    private String displayName;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @NotNull(message = "Hidden is required")
    @Column(name = "hidden", nullable = false)
    private Boolean hidden;

    @NotBlank(message = "Icon is required")
    @Column(name = "icon", nullable = false, columnDefinition = "TEXT")
    private String icon;

    @NotBlank(message = "Icon gray is required")
    @Column(name = "icon_gray", nullable = false, columnDefinition = "TEXT")
    private String iconGray;

    @Column(name = "player_percernt_unlocked")
    private Float playerPercentUnlocked;

    // Timestamp
    @Column(name = "last_time_checked", nullable = false)
    private LocalDateTime lastTimeChecked;

    // Relations
    @NotNull(message = "Game is required")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    @OneToMany(mappedBy = "achievement", fetch = FetchType.LAZY)
    private List<UserAchievements> userAchievements;

    // Methods
    @Override
    public String toString() {
        return "Achievement{id=" + achievementId + ", game ApiId=" + game.getApiId() + ", name='" + name + ", display name='" + displayName + "'}";
    }

    // Lyfecycle callbacks
    @PrePersist
    public void prePersist() {
        this.setLastTimeChecked(LocalDateTime.now());
    }

    @PreUpdate
    public void preUpdate() {
        this.setLastTimeChecked(LocalDateTime.now());
    }

    // Methods
    public void updateLastTimeChecked() {
        this.setLastTimeChecked(LocalDateTime.now());
    }
}
