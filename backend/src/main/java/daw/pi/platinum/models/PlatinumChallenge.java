package daw.pi.platinum.models;

import java.util.List;

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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "challenges", indexes = {
    @Index(name = "idx_challenges_badge_id", columnList = "badge_id")
})
public class PlatinumChallenge {

    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "challenge_id")
    private Long challengeId;

    @NotBlank(message = "Title is required")
    @Size(min = 1, max = 255, message = "Title between 1-255 chars")
    @Column(name = "title", nullable = false, length = 255)
    private String title;

    @NotBlank(message = "Description is required")
    @Column(name = "description", columnDefinition = "TEXT", nullable = false)
    private String description;

    @NotNull(message = "Requirement is required")
    @Min(value = 1, message = "Requirement must be >= 1")
    @Column(name = "requirement", nullable = false)
    private Integer requirement;

    @NotNull(message = "Points are required")
    @Min(value = 0, message = "Points must be >= 0")
    @Column(name = "points", nullable = false)
    @ColumnDefault("20")
    private Integer points;

    // Relation
    @OneToMany(mappedBy = "challenge", fetch = FetchType.LAZY)
    private List<PlatinumBadgeChallenge> badgeChallenges;

    @ManyToOne
    @JoinColumn(name = "badge_id")
    private PlatinumBadge badge;

    // Constructor
    public PlatinumChallenge(String title, String description, Integer requirement, Integer points) {
        this.setTitle(title);
        this.setDescription(description);
        this.setRequirement(requirement);
        this.setPoints(points);
        this.setBadgeChallenges(badgeChallenges);
    }
}
