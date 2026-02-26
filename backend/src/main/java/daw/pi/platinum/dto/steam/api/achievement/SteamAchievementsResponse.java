package daw.pi.platinum.dto.steam.api.achievement;

import java.util.List;

import lombok.Data;

@Data
public class SteamAchievementsResponse {

    // Attributes
    private Response response;

    // Inner classes for nested structure of Steam API response
    @Data
    public static class Response {

        private List<Games> games;
    }

    @Data
    public static class Games {

        private Integer appid;
        private Integer total_achievements;
        private List<Achievements> achievements;
    }

    @Data
    public static class Achievements {

        private Integer statid;
        private Integer bit;
        private String name;
        private String desc;
        private String icon;
        private String icon_gray;
        private Boolean hidden;
        private Float player_percent_unlocked;
    }
}
