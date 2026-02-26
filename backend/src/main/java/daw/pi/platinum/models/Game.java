package daw.pi.platinum.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
@Table(name = "games")
public class Game {

    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "game_id")
    private Long gameId;

    @NotNull(message = "API ID is required")
    @Column(name = "API_id", nullable = false, unique = true)
    private Integer apiId;

    @NotBlank(message = "Name is required")
    @Column(name = "name", nullable = false)
    private String name;

    @NotBlank(message = "Detailed description is required")
    @Column(name = "detailed_description", nullable = false, columnDefinition = "TEXT")
    private String detailedDescription;

    @NotBlank(message = "Short description is required")
    @Column(name = "short_description", nullable = false, columnDefinition = "TEXT")
    private String shortDescription;

    @NotBlank(message = "Header image is required")
    @Column(name = "header_image", nullable = false, columnDefinition = "TEXT")
    private String headerImage;

    @NotBlank(message = "Capsule image is required")
    @Column(name = "capsule_image", nullable = false, columnDefinition = "TEXT")
    private String capsuleImage;

    @NotBlank(message = "Capsule image v5 is required")
    @Column(name = "capsule_imagev5", nullable = false, columnDefinition = "TEXT")
    private String capsuleImageV5;

    @NotNull(message = "Total achievements is required")
    @Column(name = "total_achievements", nullable = false)
    private Integer totalAchievements;

    @ElementCollection
    @CollectionTable(name = "game_developers",
            joinColumns = @JoinColumn(name = "game_id"))
    @Column(name = "developers")
    private List<String> developers = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "game_publishers",
            joinColumns = @JoinColumn(name = "game_id"))
    @Column(name = "publishers")
    private List<String> publishers = new ArrayList<>();

    @NotNull(message = "Coming soon is required")
    @Column(name = "coming_soon", nullable = false)
    private Boolean comingSoon;

    @NotBlank(message = "Date is required")
    @Column(name = "date", nullable = false)
    private String date;

    // Relations
    @NotNull(message = "Genre/s required")
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "game_genres",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private Set<Genre> genres = new HashSet<>();

    @NotNull(message = "Category/ies required")
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "game_categories",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories = new HashSet<>();

    // Timestamp
    @Column(name = "last_time_checked", nullable = false)
    private LocalDateTime lastTimeChecked;

    @OneToMany(mappedBy = "game", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserGames> userGames;

    @OneToMany(mappedBy = "game", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Achievement> achievements;

    // Methods - Mainly to avoid JPA and Lombox loops when calling recurses
    @Override
    public String toString() {
        return "Game{id=" + gameId + ", apiId=" + apiId + ", name='" + name + "'}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Game)) {
            return false;
        }
        Game game = (Game) o;
        return gameId != null && gameId.equals(game.gameId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
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
