package daw.pi.platinum.dto.platinum.achievement;

import daw.pi.platinum.models.Achievement;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AchievementUnlockedDTO {

    private String name;
    private String displayName;
    private String description;
    private Boolean hidden;
    private String icon;
    private String iconGray;
    private Float playerPercentUnlocked;
    private Boolean unlocked;

    public AchievementUnlockedDTO(Achievement achievement, Boolean unlocked) {
        this.name = achievement.getName();
        this.displayName = achievement.getDisplayName();
        this.description = achievement.getDescription();
        this.hidden = achievement.getHidden();
        this.icon = achievement.getIcon();
        this.iconGray = achievement.getIconGray();
        this.playerPercentUnlocked = achievement.getPlayerPercentUnlocked();
        this.unlocked = unlocked;
    }
}
