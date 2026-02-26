package daw.pi.platinum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import daw.pi.platinum.dto.platinum.achievement.AchievementResponse;
import daw.pi.platinum.dto.platinum.game.GameResponse;
import daw.pi.platinum.dto.platinum.game.GameSummaryResponse;
import daw.pi.platinum.service.PlatinumGamesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

//--- Platinum Games Controller ---//
//
// Controller in charge of the management of games that Platinum 
// has acces and controll over
@RestController
@RequestMapping("/games")
@Tag(name = "Platinum Games", description = "Platinum games focused controller. The enpoints related to this controller are intended to manage the existing games inside the Platinum database.")
public class PlatinumGamesController {

    // Dependency injection
    @Autowired
    private PlatinumGamesService platinumGamesService;

    //--- Games Endpoints ---//
    //
    // Endpoints focused to fetch games and userGames related information
    //
    //---//
    // Get game by apiId
    @Operation(summary = "Get game by Steam API ID", description = "Get a game from the database via it Steam API ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Game found successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GameResponse.class))),
        @ApiResponse(responseCode = "403", description = "User not authorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "404", description = "Game not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/{apiId}")
    public ResponseEntity<GameResponse> getGameByApiId(@PathVariable Integer apiId) {
        // Service Call
        GameResponse response = platinumGamesService.getGameByApiId(apiId);

        // HTTP Response
        return ResponseEntity.ok(response);
    }

    // Get game achievements
    @Operation(summary = "Get game achievements by Steam API ID", description = "Get all game's achievements from the database via it Steam API ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Game achievements found successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AchievementResponse.class))),
        @ApiResponse(responseCode = "403", description = "User not authorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "404", description = "Game not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/{apiId}/achievements")
    public ResponseEntity<AchievementResponse> getGameAchievementsByApiId(@PathVariable Integer apiId) {
        // Service Call
        AchievementResponse response = platinumGamesService.getGameAchievementsByApiId(apiId);

        // HTTP Response
        return ResponseEntity.ok(response);
    }

    //--- Summary of games applying pagination ---//
    //
    //---//
    //
    // Get all games from DB paginated
    @Operation(summary = "Get games applying pagination", description = "Get all games from the database applying simple pagination")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Games found successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AchievementResponse.class))),
        @ApiResponse(responseCode = "403", description = "User not authorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/")
    public ResponseEntity<PagedModel<GameSummaryResponse>> getPaginatedAllGames(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "20") Integer size
    ) {
        // Service Call
        Pageable pageable = PageRequest.of(page, size);
        Page<GameSummaryResponse> response = platinumGamesService.getPaginatedAllGames(pageable);
    
        // HTTP Response
        return ResponseEntity.ok(new PagedModel<>(response));
    }
}
