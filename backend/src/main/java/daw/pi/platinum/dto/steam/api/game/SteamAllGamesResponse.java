package daw.pi.platinum.dto.steam.api.game;

import java.util.List;

import lombok.Data;

@Data
public class SteamAllGamesResponse {

    // Attributes
    private Response response;

    // Inner classes for nested structure of Steam API response
    @Data
    public static class Response {

        private List<App> apps;
    }

    @Data
    public static class App {

        private Integer appid;
        private String name;
    }
}
