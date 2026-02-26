package daw.pi.platinum.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "badges", indexes = {
    @Index(name = "idx_badges_name", columnList = "name")
})
public class PlatinumBadge {

    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "badge_id")
    private Long badgeId;

    @NotBlank(message = "Name is required")
    @Size(min = 1, max = 255, message = "Name between 1-255 chars")
    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @NotBlank(message = "Description is required")
    @Column(name = "description", columnDefinition = "TEXT", nullable = false)
    private String description;

    // Relation
    @OneToMany(mappedBy = "badge", fetch = FetchType.LAZY)
    private List<PlatinumBadgeChallenge> badgeChallenges;

    @OneToMany(mappedBy = "badge", fetch = FetchType.LAZY)
    private List<UserBadge> userBadges;

    // Constructor
    public PlatinumBadge(String name, String description) {
        this.setName(name);
        this.setDescription(description);
        this.setBadgeChallenges(badgeChallenges);
    }
}
