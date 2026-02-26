package daw.pi.platinum.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "rankings")
public class PlatinumRanking {

    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ranking_id")
    private Long rankingId;

    @NotBlank(message = "Title is required")
    @Column(name = "title", nullable = false, length = 255)
    private String title;

    @NotBlank(message = "Description is required")
    @Column(name = "description", length = 1000)
    private String description;

    // Relation
    @OneToMany(mappedBy = "ranking", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<UserRanking> userRankings;

    // Constructor
    public PlatinumRanking(String title, String description) {
        this.setTitle(title);
        this.setDescription(description);
    }
}
