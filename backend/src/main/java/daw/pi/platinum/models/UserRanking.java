package daw.pi.platinum.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users_rankings")
public class UserRanking {

    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ur_id")
    private Long ur_id;

    @NotNull(message = "Positon is required")
    @Column(name = "position", nullable = false)
    private Integer position;

    // Relations
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ranking_id", nullable = false)
    private PlatinumRanking ranking;

    // Constructor
    public UserRanking(User user, PlatinumRanking ranking, Integer position) {
        this.setUser(user);
        this.setRanking(ranking);
        this.setPosition(position);
    }
}
