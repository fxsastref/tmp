package daw.pi.platinum.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users", indexes = {
    @Index(name = "idx_users_email", columnList = "email", unique = true),})
public class User {

    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @NotBlank(message = "Username is required")
    @Size(min = 2, max = 32, message = "Username must be between 2 and 32 characters")
    @Column(name = "username", nullable = false, length = 32)
    private String username;

    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 64, message = "Password must be between 8 and 64 characters")
    @Column(name = "password", nullable = false, length = 64)
    private String password;

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    @Size(max = 64, message = "Email too long")
    @Column(name = "email", nullable = false, unique = true, length = 64)
    private String email;

    @Min(value = 0, message = "Level must be >= 0")
    @ColumnDefault("0")
    @Column(name = "level", nullable = false)
    private Integer level;

    @Min(value = 0, message = "Planitumns must be >= 0")
    @ColumnDefault("0")
    @Column(name = "platinums", nullable = false)
    private Integer platinums;

    @ColumnDefault("'USER'")
    @Column(nullable = false)
    private String role;

    @ColumnDefault("true")
    @Column(nullable = false)
    private boolean enabled;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // API Attributes
    @Column(name = "steam_id")
    private String steamId;

    @Column(name = "persona_name")
    private String personaName;

    @Column(name = "profile_url")
    private String profileUrl;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "avatar_medium")
    private String avatarMedium;

    @Column(name = "avatar_full")
    private String avatarFull;

    @Column(name = "community_visibility_state")
    private Integer communityVisibilityState;

    @Column(name = "profile_state")
    private Integer profileState;

    @Column(name = "time_created")
    private Long timeCreated;

    // Timestamp
    @Column(name = "last_time_checked", nullable = false)
    private LocalDateTime lastTimeChecked;

    // Relation
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<UserGames> userGames;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<UserAchievements> userAchievements;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<UserBadge> userBadges;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<PlatinumForum> forums;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<PlatinumForumReply> forumReplies;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<UserRanking> userRankings = new ArrayList<>();

    // Constructor
    public User(String name, String password, String email) {
        this.setUsername(name);
        this.setPassword(password);
        this.setEmail(email);
    }

    // Lifecycle callbacks
    @PrePersist
    protected void onCreate() {
        this.setLevel(0);
        this.setPlatinums(0);
        this.setRole("USER");
        this.setEnabled(true);
        this.setCreatedAt(LocalDateTime.now());
        this.setUpdatedAt(LocalDateTime.now());
        this.setLastTimeChecked(LocalDateTime.now());
    }

    @PreUpdate
    protected void onUpdate() {
        this.setUpdatedAt(LocalDateTime.now());
        this.setLastTimeChecked(LocalDateTime.now());
    }

    // Methods
    public void updateLastTimeChecked() {
        this.setLastTimeChecked(LocalDateTime.now());
    }

    @Override
    public String toString() {
        return "User{id=" + userId + ", username='" + username + "'}";
    }
}
