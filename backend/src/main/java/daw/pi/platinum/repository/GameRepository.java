package daw.pi.platinum.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import daw.pi.platinum.dto.platinum.game.GameSummaryResponse;
import daw.pi.platinum.models.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    Optional<Game> findByApiId(Integer apiId);

    boolean existsByApiId(Integer apiId);

    List<Game> findByApiIdIn(Set<Integer> apiIds);

    @Query(value = """
        SELECT new daw.pi.platinum.dto.platinum.game.GameSummaryResponse(
            g.apiId,
            g.name,
            g.headerImage,
            g.capsuleImage,
            g.totalAchievements
        )
        FROM Game g
        """)
    Page<GameSummaryResponse> findAllGameSummariesDTO(Pageable pageable);

    // Check if user needs Steam sync
    @Query(value = """
          SELECT EXISTS (
            SELECT 1
            FROM games g
            WHERE g.api_id = :apiId
              AND (g.last_time_checked IS NULL OR g.last_time_checked < :limitTime)
          )
          OR NOT EXISTS (
            SELECT 1
            FROM games g
            WHERE g.api_id = :apiId
          )
      """, nativeQuery = true)
    boolean needsSteamSync(@Param("apiId") Integer steamId, @Param("limitTime") LocalDateTime limitTime);
}
