package daw.pi.platinum.dto.steam.api.user;

import java.util.List;

import lombok.Data;

@Data
public class SteamPlayerSummaryResponse {

    // Attributes
    private Response response;

    // Inner classes for nested structure of Steam API response
    @Data
    public static class Response {

        private List<Player> players;
    }

    @Data
    public static class Player {

        private String steamId;
        private Integer communityvisibilitystate;
        private Integer profilestate;
        private String personaname;
        private String profileurl;

        private String avatar;
        private String avatarmedium;
        private String avatarfull;
        private String avatarhash;

        private Long lastlogoff;
        private Integer personastate;

        private String primaryclanid;
        private Long timecreated;

        private Integer personastateflags;
        private String loccountrycode;
    }
}
