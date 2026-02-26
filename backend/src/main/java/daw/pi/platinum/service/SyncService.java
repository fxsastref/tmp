package daw.pi.platinum.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import daw.pi.platinum.dto.steam.api.achievement.SteamAchievementsDetailsResponse;
import daw.pi.platinum.dto.steam.api.achievement.SteamAchievementsResponse;
import daw.pi.platinum.dto.steam.api.achievement.SteamAchievementsResponse.Achievements;
import daw.pi.platinum.dto.steam.api.game.SteamUserGamesResponse;
import daw.pi.platinum.dto.steam.api.user.SteamPlayerSummaryResponse;
import daw.pi.platinum.dto.steam.store.game.SteamGameDetailsResponse;
import daw.pi.platinum.exception.GameApiIdNotFoundException;
import daw.pi.platinum.models.Achievement;
import daw.pi.platinum.models.Category;
import daw.pi.platinum.models.Game;
import daw.pi.platinum.models.Genre;
import daw.pi.platinum.models.User;
import daw.pi.platinum.models.UserAchievements;
import daw.pi.platinum.models.UserGames;
import daw.pi.platinum.repository.AchievementRepository;
import daw.pi.platinum.repository.CategoryRepository;
import daw.pi.platinum.repository.GameRepository;
import daw.pi.platinum.repository.GenreRepository;
import daw.pi.platinum.repository.UserAchievementRepository;
import daw.pi.platinum.repository.UserGameRepository;
import daw.pi.platinum.repository.UserRepository;
import jakarta.transaction.Transactional;

@Service
public class SyncService {

    // Dependency injection
    @Autowired
    private SteamService steamService;
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private UserGameRepository userGameRepository;
    @Autowired
    private AchievementRepository achievementRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private UserAchievementRepository userAchievementRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    @Qualifier("steamSyncAchievementsApiCallExecutor")
    private Executor steamSyncAchievementsApiCallExecutor;
    @Autowired
    @Qualifier("steamSyncGamesApiCallExecutor")
    private Executor steamSyncGamesApiCallExecutor;

    // Sync game
    public void syncGame(Integer apiId) {
        System.out.println("DEBUG: Running SyncService.syncGame()");
        System.out.println("DEBUG: Running SyncService.syncGame() on thread: "
                + Thread.currentThread().getName());

        // Get game details from steam
        SteamGameDetailsResponse response = steamService.getGamesDetails(apiId);

        SteamGameDetailsResponse.Data data = response.getFirst().getData();

        // Model: Game
        boolean gameExists = gameRepository.existsByApiId(apiId);

        // If game is non existent, call steam store and fetch game details
        if (!gameExists) {
            System.out.println("DEBUG: Fetch game STEAM API - apiId: " + apiId);

            Game game = new Game();

            // Allways save the responseGame ID to avoid duplications
            game.setApiId(apiId);

            game.setName(
                    data.getName() != null && !data.getName().isBlank()
                    ? data.getName()
                    : "Unknown name");

            game.setDetailedDescription(
                    data.getDetailed_description() != null
                    && !data.getDetailed_description().isBlank()
                    ? data.getDetailed_description()
                    : "No description available.");

            game.setShortDescription(
                    data.getShort_description() != null
                    && !data.getShort_description().isBlank()
                    ? data.getShort_description()
                    : "No short description available.");

            game.setHeaderImage(
                    data.getHeader_image() != null
                    && !data.getHeader_image().isBlank()
                    ? data.getHeader_image()
                    : "No header image available.");

            game.setCapsuleImage(
                    data.getCapsule_image() != null
                    && !data.getCapsule_image().isBlank()
                    ? data.getCapsule_image()
                    : "No capsule image available.");

            game.setCapsuleImageV5(
                    data.getCapsule_imagev5() != null
                    && !data.getCapsule_imagev5().isBlank()
                    ? data.getCapsule_imagev5()
                    : "No capsule image v5 available");

            // Achievements
            game.setTotalAchievements(
                    data.getAchievements() != null
                    && data.getAchievements().getTotal() != null
                    ? data.getAchievements()
                            .getTotal()
                    : 0);

            // Categories
            game.setCategories(new HashSet<>());
            if (data.getCategories() != null) {
                for (SteamGameDetailsResponse.Data.Categories category : data
                        .getCategories()) {

                    if (category != null) {
                        // Insert either existing repo category or newly created
                        // one.
                        Category newCategory = categoryRepository
                                .findByApiId(category.getId())
                                .orElseGet(() -> {
                                    Category c = new Category();
                                    c.setApiId(category.getId());
                                    c.setDescription(category
                                            .getDescription());
                                    return categoryRepository
                                            .save(c);
                                });

                        game.getCategories().add(newCategory);
                    }
                }
            }

            // Genres
            game.setGenres(new HashSet<>());
            if (data.getGenres() != null) {
                for (SteamGameDetailsResponse.Data.Generes genre : data
                        .getGenres()) {

                    if (genre != null) {
                        // Insert either existing repo genre or newly created
                        // one.
                        Genre newGenre = genreRepository
                                .findByApiId(genre.getId())
                                .orElseGet(() -> {
                                    Genre g = new Genre();
                                    g.setApiId(genre.getId());
                                    g.setDescription(genre
                                            .getDescription());
                                    return genreRepository.save(g);
                                });

                        game.getGenres().add(newGenre);
                    }
                }
            }

            // Developers
            game.setDevelopers(
                    data.getDevelopers() != null
                    ? new ArrayList<>(data.getDevelopers())
                    : new ArrayList<>());

            // Publishers
            game.setPublishers(
                    data.getPublishers() != null
                    ? new ArrayList<>(data.getPublishers())
                    : new ArrayList<>());

            game.setComingSoon(
                    data.getRelease_date().getComing_soon() != null
                    ? data.getRelease_date().getComing_soon()
                    : false);

            game.setDate(
                    data.getRelease_date().getDate() != null
                    && !data.getRelease_date().getDate().isBlank()
                    ? data.getRelease_date()
                            .getDate()
                    : "Unknown release date");

            game.updateLastTimeChecked();

            gameRepository.save(game);
        } else {
            System.out.println(
                    "DEBUG: Fetch game PLATINUM API - apiId: " + apiId);
        }
    }

    // Sync achievement
    public void syncAchievements(Integer apiId) {
        System.out.println("DEBUG: Running SyncService.syncAchievement()");
        System.out.println("DEBUG: Running SyncService.syncAchievement() on thread: "
                + Thread.currentThread().getName());

        // Get game details from steam
        Game game = gameRepository.findByApiId(apiId).orElseThrow(() -> new GameApiIdNotFoundException(apiId));

        SteamAchievementsDetailsResponse response = steamService.getPlayerGamesAchievementsDetails(apiId);

        List<SteamAchievementsDetailsResponse.Achievements> data = response.getGame().getAvailableGameStats().getAchievements();

        Set<Achievement> newAchievements = new HashSet<>();

        for (SteamAchievementsDetailsResponse.Achievements achievementDetailsAchResponse : data) {
            Achievement achievement = new Achievement();

            achievement.setGame(game);

            achievement.setName(
                    achievementDetailsAchResponse.getName() != null
                    && !achievementDetailsAchResponse
                            .getName()
                            .isBlank()
                            ? achievementDetailsAchResponse
                                    .getName()
                            : "No name available.");

            achievement.setDisplayName(
                    achievementDetailsAchResponse.getDisplayName() != null
                    && !achievementDetailsAchResponse
                            .getDisplayName()
                            .isBlank()
                            ? achievementDetailsAchResponse
                                    .getDisplayName()
                            : "No display name available.");

            achievement.setDescription(
                    (achievementDetailsAchResponse.getDesc() != null
                    && !achievementDetailsAchResponse
                            .getDesc()
                            .isBlank())
                            ? achievementDetailsAchResponse
                                    .getDesc()
                            : (achievementDetailsAchResponse
                                    .getDescription() != null
                            && !achievementDetailsAchResponse
                                    .getDescription()
                                    .isBlank()
                                    ? achievementDetailsAchResponse
                                            .getDescription()
                                    : null));

            achievement.setHidden(
                    achievementDetailsAchResponse.getHidden() != null
                    && achievementDetailsAchResponse
                            .getHidden() == 0
                            ? false
                            : true);

            achievement.setIcon(
                    achievementDetailsAchResponse.getIcon() != null
                    && !achievementDetailsAchResponse
                            .getIcon()
                            .isBlank()
                            ? achievementDetailsAchResponse
                                    .getIcon()
                            : "No icon available.");

            achievement.setIconGray(
                    achievementDetailsAchResponse.getIcongray() != null
                    && !achievementDetailsAchResponse
                            .getIcongray()
                            .isBlank()
                            ? achievementDetailsAchResponse
                                    .getIcongray()
                            : "No gray icon available.");

            achievement.updateLastTimeChecked();

            newAchievements.add(achievement);
        }
        achievementRepository.saveAll(newAchievements);
    }

    // Sync player games
    @Transactional
    public void syncPlayerGames(User user) {
        System.out.println("DEBUG: Running SyncService.syncPlayerGames()");
        System.out.println("DEBUG: Running SyncService.syncPlayerGames() on thread: "
                + Thread.currentThread().getName());

        // Obtain all player games using the Steam API
        // The data returned here is basic and is used for UserGames and to fetch
        // GameDetails
        if (user.getSteamId() == null) {
            System.out.println("DEBUG: Running SyncService.syncPlayerGames() - User has NO Steam ID");
            return;
        }

        SteamUserGamesResponse response = steamService.getPlayersGames(
                user.getSteamId(), true, true, true, true, true);

        // Load recieved games id in a Set for efficiency
        Set<Integer> responseGamesApiId = response.getResponse().getGames()
                .stream()
                .map(SteamUserGamesResponse.Games::getAppid) // Transform each Game entity in response
                // to its appid
                .collect(Collectors.toSet());

        // Load locally saved games in a Map for efficiency and to avoid DB strain
        //
        // [ Map structure ]
        // [ Key: "gameApiId" | Value: "Achievement"]
        // [ Example ]
        // [ Key: "10" | Value: "Name: Portal, foo: bar" ]
        Map<Integer, Game> existingGamesByApiId = gameRepository
                .findByApiIdIn(responseGamesApiId)
                .stream()
                .collect(Collectors.toMap(
                        Game::getApiId,
                        game -> game));

        // Load locally saved userGames (Relations)
        //
        // [ Map structure ]
        // [ Key: "gameApiId" | Value: "Achievement"]
        // [ Example ]
        // [ Key: "10" | Value: "Name: Portal, foo: bar" ]
        Map<Integer, UserGames> existingUserGamesByGameApiId = userGameRepository
                .findByUserUserId(user.getUserId())
                .stream()
                .collect(Collectors.toMap(
                        ug -> ug.getGame().getApiId(),
                        ug -> ug));

        // Identify wich games need details fetch
        List<Integer> missingGamesIds = response.getResponse().getGames()
                .stream()
                .map(SteamUserGamesResponse.Games::getAppid)
                .filter(appId -> !existingGamesByApiId.containsKey(appId))
                .toList();

        System.out.println("Incoming games: " + responseGamesApiId.size());
        System.out.println("Existing DB games: " + existingGamesByApiId.size());
        System.out.println("Missing games: " + missingGamesIds.size());

        // Parallel API calls
        //
        // For each missingGameId call steamService.getGamesDetails()
        // and process the call in a separate thread for efficiency
        //
        // Once all threads are finished fetching gameDetails, save all the new
        // Map.Entries into a Map
        //
        // TODO: Revise the use of CompletableFuture and parallel calls. Check if is
        // better to perform sequentially to avoid hitting steam API limits and/or do it
        // in batches of 10-20 games, with a delay between batches.
        List<CompletableFuture<Map.Entry<Integer, SteamGameDetailsResponse>>> futures = missingGamesIds
                .stream()
                .map(appId -> CompletableFuture
                .supplyAsync(() -> steamService.getGamesDetails(appId),
                        steamSyncGamesApiCallExecutor)
                .exceptionally(ex -> {
                    System.err.println("Error for appId " + appId + ": "
                            + ex.getMessage());
                    return null;
                })
                .thenApply(details -> Map.entry(appId, details)))
                .toList();

        // Wait for all futures to complete
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();

        // Collect all futures results into a Map
        Map<Integer, SteamGameDetailsResponse> fetchedMap = futures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        // Prepare lists for cumulative insert
        //
        // Save all the changes in a simple array list to avoid updating the repo at
        // each iteration
        Set<Game> newGames = new HashSet<>();
        Set<UserGames> newUserGames = new HashSet<>();

        // Loop from each game recieved by the response and process Games and UserGames
        for (SteamUserGamesResponse.Games responseGame : response.getResponse().getGames()) {

            // Model: Game
            Game game = existingGamesByApiId.get(responseGame.getAppid());

            // If game is non existent, call steam store and fetch game details
            if (game == null) {
                System.out.println("DEBUG: Fetch game STEAM API - apiId: " + responseGame.getAppid());
                SteamGameDetailsResponse responseGameDetails = fetchedMap
                        .get(responseGame.getAppid());
                System.out.println("DEBUG: Running on thread: " + Thread.currentThread().getName());

                // If steam call is successfull, save new game instance
                // If steam call is NOT successfull, delete the id reference for userGame
                SteamGameDetailsResponse.AppDetails details = responseGameDetails != null
                        ? responseGameDetails.getFirst()
                        : null;

                if (details == null || !details.isSuccess() || details.getData() == null) {
                    System.out.println("DEBUG: Steam API returned no valid data for appid "
                            + responseGame.getAppid());
                } else {
                    SteamGameDetailsResponse.Data data = details.getData();

                    game = new Game();

                    // Allways save the responseGame ID to avoid duplications
                    game.setApiId(responseGame.getAppid());

                    game.setName(
                            data.getName() != null && !data.getName().isBlank()
                            ? data.getName()
                            : "Unknown name");

                    game.setDetailedDescription(
                            data.getDetailed_description() != null
                            && !data.getDetailed_description().isBlank()
                            ? data.getDetailed_description()
                            : "No description available.");

                    game.setShortDescription(
                            data.getShort_description() != null
                            && !data.getShort_description().isBlank()
                            ? data.getShort_description()
                            : "No short description available.");

                    game.setHeaderImage(
                            data.getHeader_image() != null
                            && !data.getHeader_image().isBlank()
                            ? data.getHeader_image()
                            : "No header image available.");

                    game.setCapsuleImage(
                            data.getCapsule_image() != null
                            && !data.getCapsule_image().isBlank()
                            ? data.getCapsule_image()
                            : "No capsule image available.");

                    game.setCapsuleImageV5(
                            data.getCapsule_imagev5() != null
                            && !data.getCapsule_imagev5().isBlank()
                            ? data.getCapsule_imagev5()
                            : "No capsule image v5 available");

                    // Achievements
                    game.setTotalAchievements(
                            data.getAchievements() != null
                            && data.getAchievements().getTotal() != null
                            ? data.getAchievements()
                                    .getTotal()
                            : 0);

                    // Categories
                    game.setCategories(new HashSet<>());
                    if (data.getCategories() != null) {
                        for (SteamGameDetailsResponse.Data.Categories category : data
                                .getCategories()) {

                            if (category != null) {
                                // Insert either existing repo category or newly created
                                // one.
                                Category newCategory = categoryRepository
                                        .findByApiId(category.getId())
                                        .orElseGet(() -> {
                                            Category c = new Category();
                                            c.setApiId(category.getId());
                                            c.setDescription(category
                                                    .getDescription());
                                            return categoryRepository
                                                    .save(c);
                                        });

                                game.getCategories().add(newCategory);
                            }
                        }
                    }

                    // Genres
                    game.setGenres(new HashSet<>());
                    if (data.getGenres() != null) {
                        for (SteamGameDetailsResponse.Data.Generes genre : data
                                .getGenres()) {

                            if (genre != null) {
                                // Insert either existing repo genre or newly created
                                // one.
                                Genre newGenre = genreRepository
                                        .findByApiId(genre.getId())
                                        .orElseGet(() -> {
                                            Genre g = new Genre();
                                            g.setApiId(genre.getId());
                                            g.setDescription(genre
                                                    .getDescription());
                                            return genreRepository.save(g);
                                        });

                                game.getGenres().add(newGenre);
                            }
                        }
                    }

                    // Developers
                    game.setDevelopers(
                            data.getDevelopers() != null
                            ? new ArrayList<>(data.getDevelopers())
                            : new ArrayList<>());

                    // Publishers
                    game.setPublishers(
                            data.getPublishers() != null
                            ? new ArrayList<>(data.getPublishers())
                            : new ArrayList<>());

                    game.setComingSoon(
                            data.getRelease_date().getComing_soon() != null
                            ? data.getRelease_date().getComing_soon()
                            : false);

                    game.setDate(
                            data.getRelease_date().getDate() != null
                            && !data.getRelease_date().getDate().isBlank()
                            ? data.getRelease_date()
                                    .getDate()
                            : "Unknown release date");

                    game.updateLastTimeChecked();

                    newGames.add(game);

                    // Avoid duplications
                    existingGamesByApiId.put(game.getApiId(), game);

                    // Model: UserGames
                    UserGames userGame = existingUserGamesByGameApiId.get(responseGame.getAppid());

                    // If game is non existent, call steam store and fetch game details
                    if (userGame == null) {
                        userGame = new UserGames(user, game);

                        userGame.setPlaytimeForever(
                                responseGame.getPlaytime_forever() != null
                                ? responseGame.getPlaytime_forever()
                                : 0);

                        userGame.setTimeLastPlayed(
                                responseGame.getRtime_last_played() != null
                                ? responseGame.getRtime_last_played()
                                : 0);

                        userGame.updateLastTimeChecked();

                        newUserGames.add(userGame);

                        // Avoid dupplications
                        existingUserGamesByGameApiId.put(game.getApiId(), userGame);
                    }
                    userGame.updateLastTimeChecked();
                }
            } else {
                System.out.println(
                        "DEBUG: Fetch game PLATINUM API - apiId: " + responseGame.getAppid());
            }
        }

        // Update repo's with cumulative arrays
        gameRepository.saveAll(newGames);
        userGameRepository.saveAll(newUserGames);
    }

    private String keyBuilder(int gameApiId, String name) {
        return gameApiId + "-" + name;
    }

    // Sync player's achievements when required
    public void syncPlayerAchievements(User user) {
        System.out.println("DEBUG: Running SyncService.syncPlayerAchievements()");
        System.out.println("DEBUG: Running SyncService.syncPlayerAchievements() on thread: "
                + Thread.currentThread().getName());

        if (user.getSteamId() == null) {
            System.out.println("DEBUG: Running SyncService.syncPlayerAchievements() - User has NO Steam ID");
            return;
        }

        // Check if the user's userGames are up to date
        if (userGameRepository.needsSteamSync(
                user.getUserId(),
                LocalDateTime.now().minusHours(2))) {
            syncPlayerGames(user);
        }

        // Obtain the updated userGames and save the ApiId field in a separate list
        List<UserGames> userGames = userGameRepository.findByUserUserId(user.getUserId());

        List<Integer> userGamesApiId = userGames
                .stream()
                .map(ug -> ug.getGame().getApiId())
                .toList();

        // Obtain all player achievements using the ApiId list
        SteamAchievementsResponse achievementsResponse = steamService.getPlayersAchievements(
                user.getSteamId(),
                userGamesApiId);

        // Save achievementResponse data into a Map for future procesing
        Map<Integer, SteamAchievementsResponse.Games> achievementsResponseMap = achievementsResponse
                .getResponse().getGames()
                .stream()
                .filter(game -> game.getAchievements() != null)
                .collect(Collectors.toMap(
                        SteamAchievementsResponse.Games::getAppid,
                        g -> g));

        // Load recieved achievements games id in a Set for efficiency
        Set<Integer> gamesApiId = achievementsResponse.getResponse().getGames()
                .stream()
                .flatMap(responseGame -> responseGame.getAchievements() != null
                ? responseGame.getAchievements()
                        .stream()
                        .map(steamAchievement -> responseGame.getAppid())
                : Stream.empty())
                .collect(Collectors.toSet());

        // Load locally saved achievements in a Map for efficiency and to avoid DB
        // strain
        //
        // [ Map structure ]
        // [ Key: "'gameApiId'-'achievementName'" | Value: "Achievement"]
        // [ Example ]
        // [ Key: "420-Complete The Game" | Value: "Name: Complete The Game, Game: foo,
        // Description: bar" ]
        Map<String, Achievement> existingAchievements = achievementRepository
                .findByGameApiIdIn(gamesApiId)
                .stream()
                .collect(Collectors.toMap(
                        a -> keyBuilder(
                                a.getGame().getApiId(),
                                a.getName()),
                        a -> a,
                        (a1, a2) -> a1)); // In case of duplicate keys, keep the first one

        // Load locally saved userAchievements (Relations)
        //
        // [ Map structure ]
        // [ Key: "'gameApiId'-'achievementName'" | Value: "Achievement"]
        // [ Example ]
        // [ Key: "420-Complete The Game" | Value: "Name: Complete The Game, Game: foo,
        // Description: bar" ]
        Map<String, UserAchievements> existingUserAchievements = userAchievementRepository
                .findByUserUserId(user.getUserId())
                .stream()
                .collect(Collectors.toMap(
                        ua -> keyBuilder(ua.getAchievement().getGame().getApiId(),
                                ua.getAchievement().getName()),
                        ua -> ua));

        // Identify wich games need have the achievement sync
        Set<Integer> missingGameApiIds = achievementsResponse.getResponse().getGames()
                .stream()
                .filter(game -> game.getAchievements() != null
                && game.getAchievements().stream()
                        .anyMatch(ach -> !existingAchievements.containsKey(
                        game.getAppid() + "-" + ach.getName())))
                .map(SteamAchievementsResponse.Games::getAppid)
                .collect(Collectors.toSet());

        System.out.println("Incoming games with achievements: " + gamesApiId.size());
        System.out.println("Existing DB achievements: " + existingAchievements.size());
        System.out.println("Missing achievements: " + missingGameApiIds.size());

        // Pararell API calls - Implementing syncGames logic
        System.out.println("Waiting for futures...");

        List<CompletableFuture<Map.Entry<Integer, SteamAchievementsDetailsResponse>>> futures = missingGameApiIds
                .stream()
                .map((gameApiId) -> CompletableFuture
                .supplyAsync(() -> steamService.getPlayerGamesAchievementsDetails(gameApiId),
                        steamSyncAchievementsApiCallExecutor)
                .exceptionally(ex -> {
                    System.out.println("Error for achievement " + gameApiId + ": "
                            + ex);
                    return null;
                })
                .thenApply(details -> Map.entry(gameApiId, details)))
                .toList();

        // Wait for all futures to complete
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();

        System.out.println("Futures completed");

        // Collect all futures results into a Map
        Map<Integer, SteamAchievementsDetailsResponse> achievementDetailsResponseMap = futures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        // Prepare lists for cumulative insert
        //
        // Save all the changes in a simple array list to avoid updating the repo at
        // each iteration
        Set<Achievement> newAchievements = new HashSet<>();
        Set<UserAchievements> newUserAchievements = new HashSet<>();

        // Loop from each game recieved by the achievementsResponse
        for (SteamAchievementsResponse.Games achievementResponseGame : achievementsResponse.getResponse()
                .getGames()) {

            // Get localy saved game reference from the achievementResponseGame AppId
            Integer gameApiId = achievementResponseGame.getAppid();
            Game game = gameRepository.findByApiId(gameApiId)
                    .orElseThrow(() -> new GameApiIdNotFoundException(gameApiId));

            // Get the achievementsResponse from the Steam API calling
            SteamAchievementsDetailsResponse achievementDetailsResponse = achievementDetailsResponseMap
                    .get(gameApiId);

            // If achievementDetailsResponse is null somewhere critical use mockup data.
            if (achievementDetailsResponse == null
                    || achievementDetailsResponse.getGame() == null
                    || achievementDetailsResponse.getGame().getAvailableGameStats() == null
                    || achievementDetailsResponse.getGame().getAvailableGameStats()
                            .getAchievements() == null) {

                System.out.println("DEBUG: Steam API returned no valid data for achievement");
                // TODO: Set only the achievementResponse data
            } else {

                // Process each Achievement
                for (SteamAchievementsDetailsResponse.Achievements achievementDetailsAchResponse : achievementDetailsResponse
                        .getGame()
                        .getAvailableGameStats().getAchievements()) {

                    String key = keyBuilder(
                            gameApiId,
                            achievementDetailsAchResponse.getName());

                    Achievement achievement = existingAchievements.get(key);

                    Map<String, SteamAchievementsResponse.Achievements> extraInfo = achievementsResponseMap
                            .get(gameApiId).getAchievements()
                            .stream()
                            .collect(Collectors.toMap(
                                    SteamAchievementsResponse.Achievements::getName,
                                    a -> a,
                                    (existing,replacement)->existing));

                    if (achievement == null) {
                        System.out.println("DEBUG: Fetch achievements from game STEAM API - apiId: " + gameApiId);
                        System.out.println("DEBUG: Running on thread: "
                                + Thread.currentThread().getName());

                        achievement = new Achievement();

                        achievement.setGame(game);

                        achievement.setName(
                                achievementDetailsAchResponse.getName() != null
                                && !achievementDetailsAchResponse
                                        .getName()
                                        .isBlank()
                                        ? achievementDetailsAchResponse
                                                .getName()
                                        : "No name available.");

                        achievement.setDisplayName(
                                achievementDetailsAchResponse.getDisplayName() != null
                                && !achievementDetailsAchResponse
                                        .getDisplayName()
                                        .isBlank()
                                        ? achievementDetailsAchResponse
                                                .getDisplayName()
                                        : "No display name available.");

                        achievement.setDescription(
                                (achievementDetailsAchResponse.getDesc() != null
                                && !achievementDetailsAchResponse
                                        .getDesc()
                                        .isBlank())
                                        ? achievementDetailsAchResponse
                                                .getDesc()
                                        : (achievementDetailsAchResponse
                                                .getDescription() != null
                                        && !achievementDetailsAchResponse
                                                .getDescription()
                                                .isBlank()
                                                ? achievementDetailsAchResponse
                                                        .getDescription()
                                                : null));

                        achievement.setHidden(
                                achievementDetailsAchResponse.getHidden() != null
                                && achievementDetailsAchResponse
                                        .getHidden() == 0
                                        ? false
                                        : true);

                        achievement.setIcon(
                                achievementDetailsAchResponse.getIcon() != null
                                && !achievementDetailsAchResponse
                                        .getIcon()
                                        .isBlank()
                                        ? achievementDetailsAchResponse
                                                .getIcon()
                                        : "No icon available.");

                        achievement.setIconGray(
                                achievementDetailsAchResponse.getIcongray() != null
                                && !achievementDetailsAchResponse
                                        .getIcongray()
                                        .isBlank()
                                        ? achievementDetailsAchResponse
                                                .getIcongray()
                                        : "No gray icon available.");

                        if (extraInfo.get(achievementDetailsAchResponse
                                .getDisplayName()) != null) {
                            Achievements extraInfoAchievement = extraInfo
                                    .get(achievementDetailsAchResponse
                                            .getDisplayName());

                            if (achievement.getDescription() == null) {
                                achievement.setDescription(
                                        extraInfoAchievement.getDesc() != null
                                        && !extraInfoAchievement
                                                .getDesc()
                                                .isBlank()
                                                ? extraInfoAchievement
                                                        .getDesc()
                                                : null);
                            }

                            achievement.setPlayerPercentUnlocked(
                                    extraInfoAchievement
                                            .getPlayer_percent_unlocked() != null
                                            ? extraInfoAchievement
                                                    .getPlayer_percent_unlocked()
                                            : 0);
                        }

                        newAchievements.add(achievement);

                        existingAchievements.put(key, achievement);
                    } else {
                        System.out.println("DEBUG: Fetch achievements from game PLATINUM API - apiId: " + gameApiId);
                    }

                    UserAchievements userAchievement = existingUserAchievements.get(key);

                    if (userAchievement == null) {
                        if (extraInfo.get(achievementDetailsAchResponse
                                .getDisplayName()) != null) {
                            userAchievement = new UserAchievements(user, achievement);

                            newUserAchievements.add(userAchievement);
                            existingUserAchievements.put(key, userAchievement);
                            userAchievement.updateLastTimeChecked();
                        }
                    }
                }
            }
        }
        // Update repo's with cumulative arrays
        achievementRepository.saveAll(newAchievements);
        userAchievementRepository.saveAll(newUserAchievements);
    }

    // Sync player summary
    public void syncPlayerSummary(User user) {
        // Get Steam user summary
        if (user.getSteamId() != null) {
            System.out.println("DEBUG: Running SyncService.syncPlayerSummary() - User has STEAM ID");

            SteamPlayerSummaryResponse steamAppPlayersSummaryResponse = steamService
                    .getPlayersSummary(user.getSteamId());

            System.out.println("DEBUG: Running SyncService.syncPlayerSummary() - (user.getSteamId(): " + user.getSteamId());
            System.out.println("DEBUG: Running SyncService.syncPlayerSummary() - steamAppPlayersSummaryResponse: " + steamAppPlayersSummaryResponse);
            System.out.println("DEBUG: Running SyncService.syncPlayerSummary() - steamAppPlayersSummaryResponse SteamId: " + steamAppPlayersSummaryResponse.getResponse().getPlayers().getFirst().getSteamId());

            // Add Steam summary information in our User model
            user.setPersonaName(
                    steamAppPlayersSummaryResponse.getResponse().getPlayers().getFirst().getPersonaname());
            user.setProfileUrl(
                    steamAppPlayersSummaryResponse.getResponse().getPlayers().getFirst().getProfileurl());
            user.setAvatar(steamAppPlayersSummaryResponse.getResponse().getPlayers().getFirst().getAvatar());
            user.setAvatarMedium(
                    steamAppPlayersSummaryResponse.getResponse().getPlayers().getFirst().getAvatarmedium());
            user.setAvatarFull(
                    steamAppPlayersSummaryResponse.getResponse().getPlayers().getFirst().getAvatarfull());
            user.setCommunityVisibilityState(steamAppPlayersSummaryResponse.getResponse().getPlayers().getFirst()
                    .getCommunityvisibilitystate());
            user.setProfileState(
                    steamAppPlayersSummaryResponse.getResponse().getPlayers().getFirst().getProfilestate());
            user.setTimeCreated(
                    steamAppPlayersSummaryResponse.getResponse().getPlayers().getFirst().getTimecreated());

            user.updateLastTimeChecked();

            // Save all player summary into our DB
            userRepository.save(user);
        } else {
            System.out.println("DEBUG: Running SyncService.syncPlayerSummary() - User has NOT STEAM ID");
        }
    }
}
