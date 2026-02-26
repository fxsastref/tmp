package daw.pi.platinum.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import daw.pi.platinum.models.UserGames;

@Repository
public interface UserGameRepository extends JpaRepository<UserGames, Long> {

    List<UserGames> findByUserUserId(Long userId);

    boolean existsByUserUserIdAndGameApiId(Long userId, Integer apiId);

    UserGames findByUserUserIdAndGameApiId(Long userId, Integer apiId);

    // Count the user's total achievements
    Integer countByUserUserId(Long userId);

    // Check if user needs Steam sync
    @Query(value = """
          SELECT EXISTS (
            SELECT 1
            FROM users_games ug
            WHERE ug.user_id = :userId
              AND (ug.last_time_checked IS NULL OR ug.last_time_checked < :limitTime)
          )
          OR NOT EXISTS (
            SELECT 1
            FROM users_games ug
            WHERE ug.user_id = :userId
          )
      """, nativeQuery = true)
    boolean needsSteamSync(@Param("userId") Long userId, @Param("limitTime") LocalDateTime limitTime);
}
