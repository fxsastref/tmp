package daw.pi.platinum.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import daw.pi.platinum.models.Achievement;

@Repository
public interface AchievementRepository extends JpaRepository<Achievement, Long> {

    Optional<Set<Achievement>> findByGameApiId(Integer apiId);

    List<Achievement> findByGameApiIdIn(Set<Integer> apiIds);

    // Check if user needs Steam sync
    @Query(value = """
          SELECT EXISTS (
            SELECT 1
            FROM achievements a
            WHERE a.game_id = :apiId
              AND (a.last_time_checked IS NULL OR a.last_time_checked < :limitTime)
          )
          OR NOT EXISTS (
            SELECT 1
            FROM achievements a
            WHERE a.game_id = :apiId
          )
      """, nativeQuery = true)
    boolean needsSteamSync(@Param("apiId") Integer steamId, @Param("limitTime") LocalDateTime limitTime);
}
