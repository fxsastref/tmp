package daw.pi.platinum.dto.steam.api.achievement;

import java.util.List;

import lombok.Data;

@Data
public class SteamAchievementsUnlockTimeResponse {

    // Attributes
    private PlayerStats playerstats;

    // Inner classes for nested structure of Steam API response
    @Data
    public static class PlayerStats {

        private Long steamID;
        private String gameName;
        private boolean success;
        private List<Achievement> achievements;
    }

    @Data
    public static class Achievement {

        private String apiname;
        private int achieved;
        private long unlocktime;
    }
}
