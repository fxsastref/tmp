package daw.pi.platinum.dto.platinum.game;

import daw.pi.platinum.models.Category;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GameCategoryResponse {

    private Integer apiId;
    private String description;

    public GameCategoryResponse(Category category) {
        this.apiId = category.getApiId();
        this.description = category.getDescription();
    }
}
