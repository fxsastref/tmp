package daw.pi.platinum.dto.platinum.achievement;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AchievementResponse {

    // Attributes
    private Integer gameApiId;
    private List<AchievementDTO> achievements;
}
