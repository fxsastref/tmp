package daw.pi.platinum.dto.steam.api.game;

import java.util.List;

import lombok.Data;

@Data
public class SteamUserGamesResponse {

    // Attributes
    private Response response;

    // Inner classes for nested structure of Steam API response
    @Data
    public static class Response {

        private Integer game_count;
        private List<Games> games;
    }

    @Data
    public static class Games {

        private Integer appid;
        private String name;
        private Integer playtime_forever;
        private String img_icon_url;
        private Boolean has_community_visible_stats;
        private Integer playtime_windows_forever;
        private Integer playtime_mac_forever;
        private Integer playtime_linux_forever;
        private Integer playtime_deck_forever;
        private Long rtime_last_played;
        private Integer playtime_disconnected;
    }
}
