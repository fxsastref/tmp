package daw.pi.platinum.dto.platinum.game;

import daw.pi.platinum.models.Game;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GameSummaryResponse {
    
    // Attributes
    private Integer apiId;
    private String name;
    private String headerImage;
    private String capsuleImage;
    private Integer totalAchievemenets;

    // Constructor
    public GameSummaryResponse(Game game) {
        this.setApiId(game.getApiId());
        this.setName(game.getName());
        this.setHeaderImage(game.getHeaderImage());
        this.setCapsuleImage(game.getCapsuleImage());
        this.setTotalAchievemenets(game.getTotalAchievements());
    }
}
