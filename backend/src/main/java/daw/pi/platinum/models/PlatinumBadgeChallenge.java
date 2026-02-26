package daw.pi.platinum.models;

import java.time.LocalDate;

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
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "badges_challenges", indexes = {
    @Index(name = "idx_bc_badge_id", columnList = "badge_id"),
    @Index(name = "idx_bc_challenge_id", columnList = "challenge_id")
}, uniqueConstraints = @UniqueConstraint(columnNames = {"badge_id", "challenge_id"}))
public class PlatinumBadgeChallenge {

    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bc_id")
    private Long bcId;

    @Min(0)
    @ColumnDefault("0")
    @Column(name = "progress")
    private Integer progress;

    @Column(name = "completed_date")
    private LocalDate completedDate;

    // Relations
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "badge_id")
    private PlatinumBadge badge;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "challenge_id")
    private PlatinumChallenge challenge;

    // Constructor
    public PlatinumBadgeChallenge(PlatinumBadge badge, PlatinumChallenge challenge) {
        this.setBadge(badge);
        this.setChallenge(challenge);
        this.setProgress(0);
    }
}
