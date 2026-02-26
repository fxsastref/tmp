package daw.pi.platinum.dto.platinum.game;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import daw.pi.platinum.models.Game;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GameResponse {

    // Attributes
    private Integer apiId;
    private String name;
    private String detailedDescription;
    private String shortDescription;
    private String headerImage;
    private String capsuleImage;
    private String capsuleImageV5;
    private Integer totalAchievements;
    private List<String> developers;
    private List<String> publishers;
    private Boolean comingSoon;
    private String date;
    private Set<GameGenreResponse> genres;
    private Set<GameCategoryResponse> categories;

    // Constructors
    public GameResponse(Game game) {
        this.setApiId(game.getApiId());
        this.setName(game.getName());
        this.setDetailedDescription(game.getDetailedDescription());
        this.setShortDescription(game.getShortDescription());
        this.setHeaderImage(game.getHeaderImage());
        this.setCapsuleImage(game.getCapsuleImage());
        this.setCapsuleImageV5(game.getCapsuleImageV5());
        this.setTotalAchievements(game.getTotalAchievements());
        this.setDevelopers(game.getDevelopers());
        this.setPublishers(game.getPublishers());
        this.setComingSoon(game.getComingSoon());
        this.setDate(game.getDate());
        this.setGenres(game.getGenres()
                .stream()
                .map(GameGenreResponse::new)
                .collect(Collectors.toSet()));

        this.setCategories(game.getCategories()
                .stream()
                .map(GameCategoryResponse::new)
                .collect(Collectors.toSet()));
    }
}
