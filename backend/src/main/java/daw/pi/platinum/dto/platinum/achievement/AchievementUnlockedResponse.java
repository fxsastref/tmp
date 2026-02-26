package daw.pi.platinum.dto.platinum.achievement;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AchievementUnlockedResponse {

    // Attributes
    private Integer gameApiId;
    private Set<AchievementUnlockedDTO> achievements;
}
