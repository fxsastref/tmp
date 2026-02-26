package daw.pi.platinum.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import daw.pi.platinum.models.UserAchievements;

@Repository
public interface UserAchievementRepository extends JpaRepository<UserAchievements, Long> {

    List<UserAchievements> findByUserUserId(Long userId);

    Set<UserAchievements> findByUserUserIdAndAchievementGameApiId(Long userId, Integer apiId);

    boolean existsByUserUserIdAndAchievementNameAndAchievementGameApiId(Long userId, String name, Integer apiId);

    UserAchievements findByUserUserIdAndAchievementNameAndAchievementGameApiId(Long userId, String name, Integer apiId);

    // Count the user's total achievements
    Integer countByUserUserId(Long userId);

    // Check if user needs Steam sync
    @Query(value = """
          SELECT EXISTS (
            SELECT 1
            FROM users_achievements ua
            WHERE ua.user_id = :userId
              AND (ua.last_time_checked IS NULL OR ua.last_time_checked < :limitTime)
          )
          OR NOT EXISTS (
            SELECT 1
            FROM users_achievements ua
            WHERE ua.user_id = :userId
          )
      """, nativeQuery = true)
    boolean needsSteamSync(@Param("userId") Long userId, @Param("limitTime") LocalDateTime limitTime);
}
