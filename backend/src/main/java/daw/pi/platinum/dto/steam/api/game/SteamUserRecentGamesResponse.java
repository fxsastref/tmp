package daw.pi.platinum.dto.steam.api.game;

import java.util.List;

import lombok.Data;

@Data
public class SteamUserRecentGamesResponse {

    // Attributes
    private Response response;

    // Inner classes for nested structure of Steam API response
    @Data
    public static class Response {

        private Integer total_count;
        private List<Game> games;
    }

    @Data
    public static class Game {

        private Integer appid;
        private String name;
        private Integer playtime_2weeks;
        private Integer playtime_forever;
        private String img_icon_url;
    }
}
