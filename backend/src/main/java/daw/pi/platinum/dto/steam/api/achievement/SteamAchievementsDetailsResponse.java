package daw.pi.platinum.dto.steam.api.achievement;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
public class SteamAchievementsDetailsResponse {

    // Attributes
    private Game game;

    // Inner classes for nested structure of Steam API response
    @Data
    public static class Game {

        private String gameName;
        private String gameVersion;
        private AvailableGameStats availableGameStats;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class AvailableGameStats {

        private List<Achievements> achievements;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Achievements {

        private String name;
        private String desc;
        private String description;
        private String defaultvalue;
        private String displayName;
        private Integer hidden;
        private String icon;
        private String icongray;
    }

    public Achievements getFirst() {
        return game != null && game.getAvailableGameStats() != null
                ? game.getAvailableGameStats().getAchievements().getFirst()
                : null;
    }
}
