package daw.pi.platinum.models;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "categories")
public class Category {

    // Atributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long categoryId;

    @NotNull(message = "API ID is required")
    @Column(name = "api_id", nullable = false, unique = true)
    private Integer apiId;

    @NotNull(message = "Description is required")
    @Column(name = "description", nullable = false)
    private String description;

    @ManyToMany(mappedBy = "categories")
    private Set<Game> games;

    // Methods
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Category)) {
            return false;
        }
        Category that = (Category) o;
        return categoryId != null && categoryId.equals(that.categoryId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
