package daw.pi.platinum.dto.platinum.game;

import daw.pi.platinum.models.Genre;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GameGenreResponse {

    private Integer apiId;
    private String description;

    public GameGenreResponse(Genre genre) {
        this.apiId = genre.getApiId();
        this.description = genre.getDescription();
    }
}
