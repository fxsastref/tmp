package daw.pi.platinum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import daw.pi.platinum.dto.steam.api.achievement.SteamAchievementsResponse;
import daw.pi.platinum.dto.steam.api.achievement.SteamAchievementsUnlockTimeResponse;
import daw.pi.platinum.dto.steam.api.game.SteamAllGamesResponse;
import daw.pi.platinum.dto.steam.api.game.SteamUserGamesResponse;
import daw.pi.platinum.dto.steam.api.game.SteamUserRecentGamesResponse;
import daw.pi.platinum.dto.steam.api.user.SteamPlayerSummaryResponse;
import daw.pi.platinum.dto.steam.store.game.SteamGameDetailsResponse;
import daw.pi.platinum.service.SteamService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Size;

// TODO reorganize order and check if endpoints are used or viable
@RestController
@RestControllerAdvice
@RequestMapping("/steam")
@Tag(name = "Steam", description = "Steam interaction based controller. The endpoints related to this controller are used to comunicate with Steam API and Steam Store to fetch live and updated information to store in the database")
public class SteamController {

    // Dependency injection
    @Autowired
    private SteamService steamService;

    @Operation(summary = "Get list of all games", description = "Get all the apps listed on Steam")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "List accessed successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = SteamAllGamesResponse.class))),
        @ApiResponse(responseCode = "403", description = "User not authorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/games")
    public ResponseEntity<SteamAllGamesResponse> getAppList() {
        // Service Call
        SteamAllGamesResponse response = steamService.getGames();

        // HTTP Response
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get game details", description = "Get details of one game by its Steam API ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Game accessed successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = SteamGameDetailsResponse.class))),
        @ApiResponse(responseCode = "403", description = "User not authorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "404", description = "Game not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/games/{appid}")
    public ResponseEntity<SteamGameDetailsResponse> getAppDetails(@PathVariable Integer appid) {
        // Service Call
        SteamGameDetailsResponse response = steamService.getGamesDetails(appid);

        // HTTP Response
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get user summary", description = "Get user summary by its Steam ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "User summary accessed successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = SteamPlayerSummaryResponse.class))),
        @ApiResponse(responseCode = "403", description = "User not authorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "404", description = "User not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/players/{steamId}/summary")
    // TODO change to SteamUserSummaryResponse
    public ResponseEntity<SteamPlayerSummaryResponse> getPlayersSummary(@PathVariable @Size(min = 17, max = 17) String steamId) {
        //Service Call
        SteamPlayerSummaryResponse response = steamService.getPlayersSummary(steamId);

        // HTTP Response
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get user games", description = "Get all user games by its Steam ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "User games accessed successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = SteamUserGamesResponse.class))),
        @ApiResponse(responseCode = "403", description = "User not authorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "404", description = "User not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/players/{steamId}/games")
    public ResponseEntity<SteamUserGamesResponse> getPlayerGamesAchievements(
            @PathVariable @Size(min = 17, max = 17) String steamId,
            @RequestParam(name = "include_appinfo", defaultValue = "false") boolean includeAppInfo,
            @RequestParam(name = "include_played_free_games", defaultValue = "false") boolean includePlayedFreeGames,
            @RequestParam(name = "include_free_sub", defaultValue = "false") boolean includeFreeSub,
            @RequestParam(name = "skip_unvetted_apps", defaultValue = "false") boolean skip_unvetted_apps,
            @RequestParam(name = "include_extended_info", defaultValue = "false") boolean includeExtendedInfo
    ) {
        // Service Call
        SteamUserGamesResponse response = steamService.getPlayersGames(steamId, includeAppInfo, includePlayedFreeGames, includeFreeSub, skip_unvetted_apps, includeExtendedInfo);

        // HTTP Response
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get user recently played games", description = "Get user recently played games by its Steam ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "User recently played games accessed successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = SteamUserRecentGamesResponse.class))),
        @ApiResponse(responseCode = "403", description = "User not authorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "404", description = "User not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/players/{steamId}/games/recent")
    public ResponseEntity<SteamUserRecentGamesResponse> getPlayerGamesRecent(
            @PathVariable @Size(min = 17, max = 17) String steamId,
            @RequestParam(name = "count", defaultValue = "10") Integer count
    ) {
        // Service call
        SteamUserRecentGamesResponse response = steamService.getPlayersGamesRecent(steamId, count);

        // HTTP Response
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get user achievements for a specific game", description = "Get user achievements for one specific game specified with its Steam API ID and identifying the user via its Steam ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "User achievements for game accessed successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = SteamAchievementsUnlockTimeResponse.class))),
        @ApiResponse(responseCode = "403", description = "User not authorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "404", description = "User/Game not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/players/{steamId}/games/{appid}/achievements")
    public ResponseEntity<SteamAchievementsUnlockTimeResponse> getPlayerGamesAchievements(@PathVariable @Size(min = 17, max = 17) String steamId, @PathVariable Integer appid) {
        // Service Call
        SteamAchievementsUnlockTimeResponse response = steamService.getPlayerGamesAchievements(steamId, appid);

        // HTTP Response
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get user achievements", description = "Get all user achievements by its Steam ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "User achievements accessed successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = SteamAchievementsResponse.class))),
        @ApiResponse(responseCode = "403", description = "User not authorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "404", description = "User not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/players/{steamId}/achievements")
    public ResponseEntity<SteamAchievementsResponse> getPlayerAchievements(
            @PathVariable @Size(min = 17, max = 17) String steamId,
            @RequestParam List<Integer> appids
    ) {
        // Service Call
        SteamAchievementsResponse response = steamService.getPlayersAchievements(steamId, appids);

        // HTTP Response
        return ResponseEntity.ok(response);
    }
}
