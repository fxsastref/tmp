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

import daw.pi.platinum.dto.platinum.achievement.AchievementResponse;
import daw.pi.platinum.dto.platinum.achievement.AchievementUnlockedResponse;
import daw.pi.platinum.dto.platinum.game.GameResponse;
import daw.pi.platinum.dto.platinum.user.UserResponse;
import daw.pi.platinum.service.PlatinumUsersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

//--- Platinum Users Controler ---//
//
// Controller in charge of main application logic for logged users, to retrieve
// their information and other stats
@RestController
@RequestMapping("/users")
@Tag(name = "Platinum Users", description = "Platinum user focused controller. The enpoints related to this controller are intended to manage authenticated users of the Platinum website.")
public class PlatinumUsersController {

    // Dependency injection
    @Autowired
    private PlatinumUsersService platinumUserService;

    // Search User by triad
    @Operation(summary = "Search user by triad data", description = "Search users inside the Platinum database by the triad data (Username, Steam Name, Steam Id)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User/s found successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class))),
            @ApiResponse(responseCode = "403", description = "User not authorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/search")
    public ResponseEntity<List<UserResponse>> findUsersByTriad(
            @RequestParam(value = "searchParam") String searchParam) {
        // Service Call
        List<UserResponse> response = platinumUserService.findUsersByTriad(searchParam);

        // HTTP Response
        return ResponseEntity.ok(response);
    }

    // --- Authenticated User Endpoints ---//
    //
    // Endpoints focused for the logged in user, fethcing data using
    // the token stored in SecurityContext by the Authentication JWT
    //
    // ---//
    // Get authenticaded user games
    @Operation(summary = "Get authenticated user's games", description = "Get all the games from the authenticated user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User games found successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GameResponse.class))),
            @ApiResponse(responseCode = "403", description = "User not authorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "User's games not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/me/games")
    public ResponseEntity<List<GameResponse>> getUsersMeGames() {
        // Service Call
        List<GameResponse> response = platinumUserService.getUsersMeGames();

        // HTTP Response
        return ResponseEntity.ok(response);
    }

    // Get authenticated user achievements (all) for specific game
    @Operation(summary = "Get authenticated user's achievements from specified game", description = "Get all the achievements from a specified game from the authenticated user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User achievements for specified game found successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AchievementUnlockedResponse.class))),
            @ApiResponse(responseCode = "403", description = "User not authorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "User's achievements for specified game not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/me/games/{apiId}/achievements")
    public ResponseEntity<AchievementUnlockedResponse> getUsersMeGamesAchievements(@PathVariable Integer apiId) {
        // Service Call
        AchievementUnlockedResponse response = platinumUserService.getUsersMeGamesAchievements(apiId);

        // HTTP Response
        return ResponseEntity.ok(response);
    }

    // Get authenticaded user achievements
    @Operation(summary = "Get authenticated user's all achievements", description = "Get all the achievements from the authenticated user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User achievements found successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AchievementResponse.class))),
            @ApiResponse(responseCode = "403", description = "User not authorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "User's achievements not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/me/achievements")
    public ResponseEntity<List<AchievementResponse>> getUsersMeAchievement() {
        // Service Call
        List<AchievementResponse> response = platinumUserService.getUsersMeAchievements();

        // HTTP Response
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get authenticated user summary", description = "Get summary from the authenticated user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User summary found successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class))),
            @ApiResponse(responseCode = "403", description = "User not authorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    // Get authenticaded user summary
    @GetMapping("/me/summary")
    public ResponseEntity<UserResponse> getUsersMeSummary() {
        // Service Call
        UserResponse response = platinumUserService.getUsersMeSummary();

        // HTTP Response
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Count authenticated user games", description = "Count the total number of games by a authenticated user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User games count successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class))),
            @ApiResponse(responseCode = "403", description = "User not authorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    // Count the total number of games by authenticaded user
    @GetMapping("/me/count/games")
    public ResponseEntity<Integer> countUserMeGames() {
        // Service Call
        Integer totalGames = platinumUserService.countUserMeGames();

        // HTTP Response
        return ResponseEntity.ok(totalGames);
    }

    @Operation(summary = "Count authenticated user achievements", description = "Count the total number of achievements by a authenticated user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User achievements count successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class))),
            @ApiResponse(responseCode = "403", description = "User not authorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    // Count the total number of achievements by authenticaded user
    @GetMapping("/me/count/achievements")
    public ResponseEntity<Integer> countUserMeAchievements() {
        // Service Call
        Integer totalAchievements = platinumUserService.countUserMeAchievements();

        // HTTP Response
        return ResponseEntity.ok(totalAchievements);
    }

    // --- Specified User Endpoints ---//
    //
    // Endpoints focused for fethcing data using a specified username
    //
    // ---//
    // Get specified user games
    @Operation(summary = "Get specified user's games", description = "Get all the games from the specified user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User games found successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GameResponse.class))),
            @ApiResponse(responseCode = "403", description = "User not authorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "User's games not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/{steamId}/games")
    public ResponseEntity<List<GameResponse>> getUsersSteamIdGames(@PathVariable String steamId) {
        // Service Call
        List<GameResponse> response = platinumUserService.getUsersSteamIdGames(steamId);

        // HTTP Response
        return ResponseEntity.ok(response);
    }

    // Get specified user achievements (all) for specific game
    @Operation(summary = "Get specified user's achievements from specified game", description = "Get all the achievements from a specified game from the specified user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User achievements for specified game found successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AchievementUnlockedResponse.class))),
            @ApiResponse(responseCode = "403", description = "User not authorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "User's achievements for specified game not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/{steamId}/games/{apiId}/achievements")
    public ResponseEntity<AchievementUnlockedResponse> getUsersSteamIdGamesAchievements(@PathVariable String steamId,
            @PathVariable Integer apiId) {
        // Service Call
        AchievementUnlockedResponse response = platinumUserService.getUsersSteamIdGamesAchievements(steamId, apiId);

        // HTTP Response
        return ResponseEntity.ok(response);
    }

    // Get specified user achievements
    @Operation(summary = "Get specified user's all achievements", description = "Get all the achievements from the specified user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User achievements found successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AchievementResponse.class))),
            @ApiResponse(responseCode = "403", description = "User not authorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "User's achievements not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/{steamId}/achievements")
    public ResponseEntity<List<AchievementResponse>> getUsersSteamIdAchievements(@PathVariable String steamId) {
        // Service Call
        List<AchievementResponse> response = platinumUserService.getUsersSteamIdAchievements(steamId);

        // HTTP Response
        return ResponseEntity.ok(response);
    }

    // Get specified user summary
    @Operation(summary = "Get specified user summary", description = "Get summary from the specified user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User summary found successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class))),
            @ApiResponse(responseCode = "403", description = "User not authorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/{steamId}/summary")
    public ResponseEntity<UserResponse> getUsersSteamIdSummary(@PathVariable String steamId) {
        // Service Call
        UserResponse response = platinumUserService.getUsersSteamIdSummary(steamId);

        // HTTP Response
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Count specified user games", description = "Count the total number of games by a specified user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User games count successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class))),
            @ApiResponse(responseCode = "403", description = "User not authorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    // Count the total number of games by a user
    @GetMapping("/{steamId}/count/games")
    public ResponseEntity<Integer> countUsersSteamIdGames(@PathVariable String steamId) {
        // Service Call
        Integer totalGames = platinumUserService.countUsersSteamIdGames(steamId);

        // HTTP Response
        return ResponseEntity.ok(totalGames);
    }

    @Operation(summary = "Count specified user achievements", description = "Count the total number of achievements by a specified user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User achievements count successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class))),
            @ApiResponse(responseCode = "403", description = "User not authorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    // Count the total number of achievements by a user
    @GetMapping("/{steamId}/count/achievements")
    public ResponseEntity<Integer> countUsersSteamIdAchievements(@PathVariable String steamId) {
        // Service Call
        Integer totalAchievements = platinumUserService.countUsersSteamIdAchievements(steamId);

        // HTTP Response
        return ResponseEntity.ok(totalAchievements);
    }
}
