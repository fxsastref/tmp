package daw.pi.platinum.service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import daw.pi.platinum.dto.platinum.achievement.AchievementDTO;
import daw.pi.platinum.dto.platinum.achievement.AchievementResponse;
import daw.pi.platinum.dto.platinum.achievement.AchievementUnlockedDTO;
import daw.pi.platinum.dto.platinum.achievement.AchievementUnlockedResponse;
import daw.pi.platinum.dto.platinum.game.GameResponse;
import daw.pi.platinum.dto.platinum.user.UserResponse;
import daw.pi.platinum.exception.GameApiIdNotFoundException;
import daw.pi.platinum.exception.SteamIdNotFoundException;
import daw.pi.platinum.exception.UserNotFoundTriadException;
import daw.pi.platinum.exception.UsernameNotFoundException;
import daw.pi.platinum.models.Achievement;
import daw.pi.platinum.models.User;
import daw.pi.platinum.models.UserAchievements;
import daw.pi.platinum.repository.AchievementRepository;
import daw.pi.platinum.repository.UserAchievementRepository;
import daw.pi.platinum.repository.UserGameRepository;
import daw.pi.platinum.repository.UserRepository;

@Service
public class PlatinumUsersService {

    // Dependency injection
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserGameRepository userGameRepository;
    @Autowired
    private UserAchievementRepository userAchievementRepository;
    @Autowired
    private AchievementRepository achievementRepository;
    @Autowired
    private SyncService syncService;

    // Search user by triad (username, steam username, steam id)
    public List<UserResponse> findUsersByTriad(String searchParam) {
        System.out.println("DEBUG: Running PlatinumUsersService.findUsersByTriad()");

        if (searchParam != null && !searchParam.isBlank()) {
            return userRepository.findUsersByTriad(searchParam)
                    .orElseThrow(() -> new UserNotFoundTriadException(searchParam));
        } else {
            return null;
        }
    }

    // --- Authenticated User Methods ---//
    //
    // Methods used by the authenticaded user endpoints
    //
    // ---//
    // Get authenticated user games
    public List<GameResponse> getUsersMeGames() {

        System.out.println("DEBUG: Running PlatinumUsersService.getUsersMeGames()");
        // Get current username
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));

        // Sync Steam user games if it is necessary
        if (userGameRepository.needsSteamSync(user.getUserId(), LocalDateTime.now().minusHours(2))) {
            System.out.println("DEBUG: Running PlatinumUsersService.getUsersMeGames() | Sync Games Required");
            syncService.syncPlayerGames(user);
        } else {
            System.out.println("DEBUG: Running PlatinumUsersService.getUsersMeGames() | Sync Games NOT Required");
        }

        return userGameRepository.findByUserUserId(user.getUserId()).stream()
                .map(ug -> new GameResponse(ug.getGame()))
                .toList();
    }

    // Get authenticated user achievements (all) for specific game
    public AchievementUnlockedResponse getUsersMeGamesAchievements(Integer apiId) {

        System.out.println("DEBUG: Running PlatinumUsersService.getUsersMeGamesAchievements()");
        // Get current username
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
        Long userId = user.getUserId();

        // Sync Steam user games if it is necessary
        if (userGameRepository.needsSteamSync(user.getUserId(), LocalDateTime.now().minusHours(2))) {
            System.out
                    .println("DEBUG: Running PlatinumUsersService.getUsersMeGamesAchievements() | Sync Games Required");
            syncService.syncPlayerGames(user);
        } else {
            System.out.println(
                    "DEBUG: Running PlatinumUsersService.getUsersMeGamesAchievements() | Sync Games NOT Required");
        }
        // Sync Steam user achievements if it is necessary
        if (userAchievementRepository.needsSteamSync(user.getUserId(), LocalDateTime.now().minusHours(2))) {
            System.out.println(
                    "DEBUG: Running PlatinumUsersService.getUsersMeGamesAchievements() | Sync Achievements Required");
            syncService.syncPlayerAchievements(user);
        } else {
            System.out.println(
                    "DEBUG: Running PlatinumUsersService.getUsersMeGamesAchievements() | Sync Achievements NOT Required");
        }

        Set<Achievement> gameAchievements = achievementRepository.findByGameApiId(apiId)
                .orElseThrow(() -> new GameApiIdNotFoundException(apiId));
        Set<UserAchievements> userAchievements = userAchievementRepository
                .findByUserUserIdAndAchievementGameApiId(userId, apiId);
        Set<AchievementUnlockedDTO> achievementUnlockedDTOs = new HashSet<>();

        Map<String, Achievement> gameAchievementsMap = gameAchievements
                .stream()
                .collect(Collectors.toMap(
                        Achievement::getDisplayName,
                        a -> a));

        Map<String, UserAchievements> userAchievementsMap = userAchievements
                .stream()
                .collect(Collectors.toMap(
                        ua -> ua.getAchievement().getDisplayName(),
                        ua -> ua));

        for (Achievement gameAchievement : gameAchievements) {
            String name = gameAchievement.getDisplayName();

            Boolean isUnlocked = userAchievementsMap.containsKey(name);

            achievementUnlockedDTOs.add(new AchievementUnlockedDTO(gameAchievementsMap.get(name), isUnlocked));
        }

        return new AchievementUnlockedResponse(apiId, achievementUnlockedDTOs);
    }

    // Get authenticated user achievements
    public List<AchievementResponse> getUsersMeAchievements() {

        System.out.println("DEBUG: Running PlatinumUsersService.getUsersMeAchievements()");

        // Get current username
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));

        // Sync Steam user achievements if it is necessary
        if (userAchievementRepository.needsSteamSync(user.getUserId(), LocalDateTime.now().minusHours(2))) {
            System.out.println(
                    "DEBUG: Running PlatinumUsersService.getUsersMeAchievements() | Sync Achievements Required");
            syncService.syncPlayerAchievements(user);
        } else {
            System.out.println(
                    "DEBUG: Running PlatinumUsersService.getUsersMeAchievements() | Sync Achievements NOT Required");
        }

        // Fetch all user achievements
        List<UserAchievements> userAchievements = userAchievementRepository.findByUserUserId(user.getUserId());

        // Group by gameId
        Map<Integer, List<AchievementDTO>> userAchievementsGrouped = userAchievements
                .stream()
                .collect(Collectors.groupingBy(
                        ua -> ua.getAchievement().getGame().getApiId(),
                        Collectors.mapping(
                                ua -> new AchievementDTO(ua.getAchievement()),
                                Collectors.toList())));

        // Convert Map - > List
        return userAchievementsGrouped.entrySet()
                .stream()
                .map(entry -> new AchievementResponse(
                        entry.getKey(),
                        entry.getValue()))
                .toList();
    }

    // Get authenticated user summary
    public UserResponse getUsersMeSummary() {

        System.out.println("DEBUG: Running PlatinumUsersService.getUsersMeSummary()");

        // Get current username
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));

        // Sync Steam user summary if it is necessary
        if (userRepository.needsSteamSync(user.getUserId(), LocalDateTime.now().minusHours(0))) {
            System.out.println("DEBUG: Running PlatinumUsersService.getUsersMeSummary() | Sync Summary Required");
            syncService.syncPlayerSummary(user);
        } else {
            System.out.println("DEBUG: Running PlatinumUsersService.getUsersMeSummary() | Sync Summary NOT Required");
        }

        return new UserResponse(user);
    }

    // Count the total number of games by a user
    public Integer countUserMeGames() {
        System.out.println("DEBUG: Running PlatinumUsersService.countUserMeGames()");

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));

        return userGameRepository.countByUserUserId(user.getUserId());
    }

    // Count the total number of achievements by a user
    public Integer countUserMeAchievements() {
        System.out.println("DEBUG: Running PlatinumUsersService.countUserMeAchievements()");

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));

        return userAchievementRepository.countByUserUserId(user.getUserId());
    }

    // --- Specified User Methods ---//
    //
    // Methods used by the specified user endpoints
    //
    // ---//
    // Get user games
    public List<GameResponse> getUsersSteamIdGames(String steamId) {

        System.out.println("DEBUG: Running PlatinumUsersService.getUsersSteamIdGames()");

        User user = userRepository.findBySteamId(steamId).orElseThrow(() -> new SteamIdNotFoundException(steamId));

        // Sync Steam user games if it is necessary
        if (userGameRepository.needsSteamSync(user.getUserId(), LocalDateTime.now().minusHours(2))) {
            System.out.println("DEBUG: Running PlatinumUsersService.getUsersSteamIdGames() | Sync Games Required");
            syncService.syncPlayerGames(user);
        } else {
            System.out.println("DEBUG: Running PlatinumUsersService.getUsersSteamIdGames() | Sync Games NOT Required");
        }

        return userGameRepository.findByUserUserId(user.getUserId()).stream()
                .map(ug -> new GameResponse(ug.getGame()))
                .toList();
    }

    // Get user achievements (all) for specific game
    public AchievementUnlockedResponse getUsersSteamIdGamesAchievements(String steamId, Integer apiId) {

        System.out.println("DEBUG: Running PlatinumUsersService.getUsersMeGamesAchievements()");
        // Get current username
        User user = userRepository.findBySteamId(steamId).orElseThrow(() -> new SteamIdNotFoundException(steamId));
        Long userId = user.getUserId();

        // Sync Steam user games if it is necessary
        if (userGameRepository.needsSteamSync(user.getUserId(), LocalDateTime.now().minusHours(2))) {
            System.out
                    .println("DEBUG: Running PlatinumUsersService.getUsersMeGamesAchievements() | Sync Games Required");
            syncService.syncPlayerGames(user);
        } else {
            System.out.println(
                    "DEBUG: Running PlatinumUsersService.getUsersMeGamesAchievements() | Sync Games NOT Required");
        }
        // Sync Steam user achievements if it is necessary
        if (userAchievementRepository.needsSteamSync(user.getUserId(), LocalDateTime.now().minusHours(2))) {
            System.out.println(
                    "DEBUG: Running PlatinumUsersService.getUsersMeGamesAchievements() | Sync Achievements Required");
            syncService.syncPlayerAchievements(user);
        } else {
            System.out.println(
                    "DEBUG: Running PlatinumUsersService.getUsersMeGamesAchievements() | Sync Achievements NOT Required");
        }

        Set<Achievement> gameAchievements = achievementRepository.findByGameApiId(apiId)
                .orElseThrow(() -> new GameApiIdNotFoundException(apiId));
        Set<UserAchievements> userAchievements = userAchievementRepository
                .findByUserUserIdAndAchievementGameApiId(userId, apiId);
        Set<AchievementUnlockedDTO> achievementUnlockedDTOs = new HashSet<>();

        Map<String, Achievement> gameAchievementsMap = gameAchievements
                .stream()
                .collect(Collectors.toMap(
                        Achievement::getDisplayName,
                        a -> a,
                        (existing, replacement) -> existing));

        Map<String, UserAchievements> userAchievementsMap = userAchievements
                .stream()
                .collect(Collectors.toMap(
                        ua -> ua.getAchievement().getDisplayName(),
                        ua -> ua));

        for (Achievement gameAchievement : gameAchievements) {
            String name = gameAchievement.getDisplayName();

            Boolean isUnlocked = userAchievementsMap.containsKey(name);

            achievementUnlockedDTOs.add(new AchievementUnlockedDTO(gameAchievementsMap.get(name), isUnlocked));
        }

        return new AchievementUnlockedResponse(apiId, achievementUnlockedDTOs);
    }

    // Get user achievements
    public List<AchievementResponse> getUsersSteamIdAchievements(String steamId) {

        System.out.println("DEBUG: Running PlatinumUsersService.getUsersSteamIdAchievements()");

        User user = userRepository.findBySteamId(steamId).orElseThrow(() -> new SteamIdNotFoundException(steamId));

        // Sync Steam user achievements if it is necessary
        if (userAchievementRepository.needsSteamSync(user.getUserId(), LocalDateTime.now().minusHours(2))) {
            System.out.println(
                    "DEBUG: Running PlatinumUsersService.getUsersSteamIdAchievements() | Sync Achievements Required");
            syncService.syncPlayerAchievements(user);
        } else {
            System.out.println(
                    "DEBUG: Running PlatinumUsersService.getUsersSteamIdAchievements() | Sync Achievements NOT Required");
        }

        // Fetch all user achievements
        List<UserAchievements> userAchievements = userAchievementRepository.findByUserUserId(user.getUserId());

        // Group by gameId
        Map<Integer, List<AchievementDTO>> userAchievementsGrouped = userAchievements
                .stream()
                .collect(Collectors.groupingBy(
                        ua -> ua.getAchievement().getGame().getApiId(),
                        Collectors.mapping(
                                ua -> new AchievementDTO(ua.getAchievement()),
                                Collectors.toList())));

        // Convert Map -> List
        return userAchievementsGrouped.entrySet()
                .stream()
                .map(entry -> new AchievementResponse(
                        entry.getKey(),
                        entry.getValue()))
                .toList();
    }

    // Get user summary
    public UserResponse getUsersSteamIdSummary(String steamId) {
        System.out.println("DEBUG: Running PlatinumUsersService.getUsersSteamIdSummary()");

        User user = userRepository.findBySteamId(steamId).orElseThrow(() -> new SteamIdNotFoundException(steamId));

        // Sync Steam user summary if it is necessary
        if (userRepository.needsSteamSync(user.getUserId(), LocalDateTime.now().minusHours(2))) {
            syncService.syncPlayerSummary(user);
        }

        return new UserResponse(user);
    }

    // Count the total number of games by a user
    public Integer countUsersSteamIdGames(String steamId) {
        System.out.println("DEBUG: Running PlatinumUsersService.countUsersSteamIdGames()");

        User user = userRepository.findBySteamId(steamId).orElseThrow(() -> new SteamIdNotFoundException(steamId));

        return userGameRepository.countByUserUserId(user.getUserId());
    }

    // Count the total number of achievements by a user
    public Integer countUsersSteamIdAchievements(String steamId) {
        System.out.println("DEBUG: Running PlatinumUsersService.countUsersSteamIdAchievements()");

        User user = userRepository.findBySteamId(steamId).orElseThrow(() -> new SteamIdNotFoundException(steamId));

        return userAchievementRepository.countByUserUserId(user.getUserId());
    }
}
