package daw.pi.platinum.models;

import java.time.LocalDate;

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
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users_badges",
        indexes = {
            @Index(name = "idx_ub_user_id", columnList = "user_id"),
            @Index(name = "idx_ub_badge_id", columnList = "badge_id")
        })
public class UserBadge {

    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ub_id")
    private Long ubId;

    @Column(name = "obtained_date", nullable = false)
    private LocalDate unlockedDate;

    // Relations
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "badge_id")
    private PlatinumBadge badge;

    // Constructor
    public UserBadge(User user, PlatinumBadge badge) {
        this.setUser(user);
        this.setBadge(badge);
        this.setUnlockedDate(LocalDate.now());
    }
}
