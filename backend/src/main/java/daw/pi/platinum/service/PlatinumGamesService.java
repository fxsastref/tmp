package daw.pi.platinum.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import daw.pi.platinum.dto.platinum.achievement.AchievementDTO;
import daw.pi.platinum.dto.platinum.achievement.AchievementResponse;
import daw.pi.platinum.dto.platinum.game.GameResponse;
import daw.pi.platinum.dto.platinum.game.GameSummaryResponse;
import daw.pi.platinum.exception.GameApiIdNotFoundException;
import daw.pi.platinum.repository.AchievementRepository;
import daw.pi.platinum.repository.GameRepository;

@Service
public class PlatinumGamesService {

    // Dependency injection
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private AchievementRepository achievementRepository;
    @Autowired
    private SyncService syncService;

    // Get game by id
    public GameResponse getGameByApiId(Integer apiId) {

        // If game is non existent or needs sync, sync game data
        if (gameRepository.findByApiId(apiId) == null || gameRepository.needsSteamSync(apiId, LocalDateTime.now().minusHours(2))) {
            System.out.println("DEBUG: Running PlatinumGamesService.getGameByApiId() | Sync Game Required");
            syncService.syncGame(apiId);
        } else {
            System.out.println("DEBUG: Running PlatinumGamesService.getGameByApiId() | Sync Game NOT Required");
        }

        return new GameResponse(gameRepository.findByApiId(apiId)
                .orElseThrow(() -> new GameApiIdNotFoundException(apiId)));
    }

    // Get game achievements
    public AchievementResponse getGameAchievementsByApiId(Integer apiId) {

        // Sync Steam user achievements if it is necessary
        if (achievementRepository.needsSteamSync(apiId, LocalDateTime.now().minusHours(2))) {
            System.out.println(
                    "DEBUG: Running PlatinumGamesService.getGameAchievementsByApiId() | Sync Achievements Required");
            syncService.syncAchievements(apiId);
        } else {
            System.out.println(
                    "DEBUG: Running PlatinumGamesService.getGameAchievementsByApiId() | Sync Achievements NOT Required");
        }

        List<AchievementDTO> achievementDTOs = achievementRepository
                .findByGameApiId(apiId)
                .orElseThrow(() -> new GameApiIdNotFoundException(apiId))
                .stream()
                .map(a -> new AchievementDTO(a))
                .toList();

        return new AchievementResponse(apiId, achievementDTOs);
    }

    // Get all games in transaction mode for pagination
    public Page<GameSummaryResponse> getPaginatedAllGames(Pageable pageable) {
        System.out.println("DEBUG: Running PlatinumGamesService.getPaginatedAllGames()");

        return gameRepository.findAllGameSummariesDTO(pageable);
    }
}
