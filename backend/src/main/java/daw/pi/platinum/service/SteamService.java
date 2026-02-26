package daw.pi.platinum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import daw.pi.platinum.config.SteamProperties;
import daw.pi.platinum.dto.steam.api.achievement.SteamAchievementsDetailsResponse;
import daw.pi.platinum.dto.steam.api.achievement.SteamAchievementsResponse;
import daw.pi.platinum.dto.steam.api.achievement.SteamAchievementsUnlockTimeResponse;
import daw.pi.platinum.dto.steam.api.game.SteamAllGamesResponse;
import daw.pi.platinum.dto.steam.api.game.SteamUserGamesResponse;
import daw.pi.platinum.dto.steam.api.game.SteamUserRecentGamesResponse;
import daw.pi.platinum.dto.steam.api.user.SteamPlayerSummaryResponse;
import daw.pi.platinum.dto.steam.store.game.SteamGameDetailsResponse;

@Service
public class SteamService {

    // TODO: Revise names for functions and DTO:
    // If the function is to get x from user, start wit getPlayerX
    // If the function is to get x from game, start with getGameX...
    //
    // Dependency injection
    @Autowired
    private SteamProperties steamProperties;
    @Autowired
    private RestTemplate restTemplate;

    // Get all Steam apps
    public SteamAllGamesResponse getGames() {
        String url = steamProperties.getApiUrl() + "/IStoreService/GetAppList/v1/?key=" + steamProperties.getApiKey()
                + "&include_games=true&include_dlc=false&include_software=false&max_results=200";
        return restTemplate.getForObject(url, SteamAllGamesResponse.class);
    }

    // Get Setam game details
    public SteamGameDetailsResponse getGamesDetails(Integer appid) {
        String url = steamProperties.getStoreUrl() + "/api/appdetails?appids=" + appid;
        return restTemplate.getForObject(url, SteamGameDetailsResponse.class);
    }

    // Get player summary
    public SteamPlayerSummaryResponse getPlayersSummary(String steamId) {
        String url = steamProperties.getApiUrl() + "/ISteamUser/GetPlayerSummaries/v2/?key=" + steamProperties.getApiKey() + "&steamIds=" + steamId;
        return restTemplate.getForObject(url, SteamPlayerSummaryResponse.class);
    }

    // Get player games
    public SteamUserGamesResponse getPlayersGames(String steamId, Boolean includeAppInfo, Boolean includePlayedFreeGames, Boolean includeFreeSub, Boolean skipUnvettedApps, Boolean includeExtendedInfo
    ) {
        // Start with base URL
        String url = steamProperties.getApiUrl() + "/IPlayerService/GetOwnedGames/v1/?key="
                + steamProperties.getApiKey()
                + "&steamId=" + steamId;

        // Add optional flags if not null
        if (Boolean.TRUE.equals(includeAppInfo)) {
            url += "&include_appinfo=true";
        }
        if (Boolean.TRUE.equals(includePlayedFreeGames)) {
            url += "&include_played_free_games=true";
        }
        if (Boolean.TRUE.equals(includeFreeSub)) {
            url += "&include_free_sub=true";
        }
        if (Boolean.TRUE.equals(skipUnvettedApps)) {
            url += "&skip_unvetted_apps=true";
        }
        if (Boolean.TRUE.equals(includeExtendedInfo)) {
            url += "&include_extended_appinfo=true";
        }

        return restTemplate.getForObject(url, SteamUserGamesResponse.class);
    }

    // Get player games last played
    public SteamUserRecentGamesResponse getPlayersGamesRecent(String steamId, Integer count) {
        String url = steamProperties.getApiUrl() + "/IPlayerService/GetRecentlyPlayedGames/v1/?key="
                + steamProperties.getApiKey()
                + "&steamId=" + steamId + "&count=" + count;
        return restTemplate.getForObject(url, SteamUserRecentGamesResponse.class);
    }

    // Get player achievements of 1 game
    public SteamAchievementsUnlockTimeResponse getPlayerGamesAchievements(String steamId, Integer appid) {
        String url = steamProperties.getApiUrl() + "/ISteamUserStats/GetPlayerAchievements/v1/?key=" + steamProperties.getApiKey()
                + "&steamId=" + steamId + "&appid=" + appid;
        return restTemplate.getForObject(url, SteamAchievementsUnlockTimeResponse.class);
    }

    // Get player achievements all
    public SteamAchievementsResponse getPlayersAchievements(String steamId, List<Integer> appids) {
        String url = steamProperties.getApiUrl() + "/IPlayerService/GetTopAchievementsForGames/v1/?key=" + steamProperties.getApiKey()
                + "&steamId=" + steamId + "&max_achievements=10000";
        for (Integer x = 0; x < appids.size(); x++) {
            url = url + "&appids[" + x + "]=" + appids.get(x);
        }

        return restTemplate.getForObject(url, SteamAchievementsResponse.class);
    }

    // Get player achievements detiails of 1 game
    public SteamAchievementsDetailsResponse getPlayerGamesAchievementsDetails(Integer appid) {
        String url = steamProperties.getApiUrl() + "/ISteamUserStats/GetSchemaForGame/v2/?key=" + steamProperties.getApiKey() + "&appid=" + appid;
        return restTemplate.getForObject(url, SteamAchievementsDetailsResponse.class);
    }
}
