package daw.pi.platinum.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import daw.pi.platinum.dto.platinum.user.UserResponse;
import daw.pi.platinum.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUsername(String username);

  boolean existsByUsername(String username);

    Optional<User> findBySteamId(String steamId);

    boolean existsBySteamId(String steamId);

  boolean existsByEmail(String email);

  // Find by username/steam username/steam id
  @Query(value = """
      SELECT
        u.user_id,
        u.username,
        u.email,
        u.level,
        u.platinums,
        u.role,
        u.enabled,
        u.created_at,
        u.updated_at,
        u.steam_id,
        u.persona_name,
        u.profile_url,
        u.avatar,
        u.avatar_medium,
        u.avatar_full,
        u.community_visibility_state,
        u.profile_state,
        u.time_created

      FROM
        users u
      WHERE
        u.username LIKE CONCAT('%', :searchParam,'%')
      OR
        u.persona_name LIKE CONCAT('%', :searchParam,'%')
      OR
        u.steam_id::text LIKE CONCAT('%', :searchParam,'%')
      """, nativeQuery = true)
  Optional<List<UserResponse>> findUsersByTriad(@Param("searchParam") String searchParam);

  // Check if user needs Steam sync
  @Query(value = """
      SELECT COALESCE(
        (
          SELECT (
            u.steam_id IS NULL
            OR u.last_time_checked IS NULL
            OR u.last_time_checked < :limitTime
          )
            FROM users u
            WHERE u.user_id = :userId
        ),
        true
      )
      """, nativeQuery = true)
  boolean needsSteamSync(@Param("userId") Long userId, @Param("limitTime") LocalDateTime limitTime);
}
