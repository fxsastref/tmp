package daw.pi.platinum.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users_achievements",
        indexes = {
            @Index(name = "idx_ua_user_id", columnList = "user_id"),
            @Index(name = "idx_ua_user_id_and_achievement_id", columnList = "user_id, achievement_id"),
            @Index(name = "idx_ua_achievement_id", columnList = "achievement_id")
        })
public class UserAchievements {

    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ua_id")
    private Long uaId;

    // Timestamp
    @Column(name = "last_time_checked", nullable = false)
    private LocalDateTime lastTimeChecked;

    // Relations
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "achievement_id")
    private Achievement achievement;

    // Constructor
    public UserAchievements(User user, Achievement achievement) {
        this.setUser(user);
        this.setAchievement(achievement);
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

    @Override
    public String toString() {
        return "UserAchievement{id=" + uaId + ", user_id=" + user.getUserId() + ", achievement_id=" + achievement.getAchievementId() + "}";
    }
}
