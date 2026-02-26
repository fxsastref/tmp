package daw.pi.platinum.models;

import java.time.LocalDateTime;

import org.hibernate.annotations.ColumnDefault;

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
@Table(name = "users_games",
        indexes = {
            @Index(name = "idx_ug_user_id", columnList = "user_id"),
            @Index(name = "idx_ug_user_id_and_game_id", columnList = "user_id, game_id"),
            @Index(name = "idx_ug_game_id", columnList = "game_id")
        })
public class UserGames {

    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ug_id", nullable = false)
    private Long ugId;

    @ColumnDefault("0")
    @Column(name = "playtime_forever", nullable = false)
    private Integer playtimeForever;

    @ColumnDefault("0")
    @Column(name = "time_last_played", nullable = false)
    private Long timeLastPlayed;

    // Timestamp
    @Column(name = "last_time_checked", nullable = false)
    private LocalDateTime lastTimeChecked;

    // Relations
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id")
    private Game game;

    // Constructor
    public UserGames(User user, Game game) {
        this.setUser(user);
        this.setGame(game);
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
        return "UserGame{id=" + ugId + "}";
    }
}
