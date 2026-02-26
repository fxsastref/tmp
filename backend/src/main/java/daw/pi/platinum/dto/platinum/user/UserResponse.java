package daw.pi.platinum.dto.platinum.user;

import java.time.LocalDateTime;

import daw.pi.platinum.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponse {

    // User annotationsAttributes
    private Long userId;
    private String username;
    private String email;
    private Integer level;
    private Integer platinums;
    private String role;
    private boolean enabled;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    // API Attributes
    private String steamId;
    private String personaName;
    private String profileUrl;
    private String avatar;
    private String avatarMedium;
    private String avatarFull;
    private Integer communityVisibilityState;
    private Integer profileState;
    private Long timeCreated;

    // Constructor
    public UserResponse(User user) {
        this.setUsername(user.getUsername());
        this.setEmail(user.getEmail());
        this.setLevel(user.getLevel());
        this.setPlatinums(user.getPlatinums());
        this.setRole(user.getRole());
        this.setEnabled(user.isEnabled());
        this.setSteamId(user.getSteamId());
        this.setPersonaName(user.getPersonaName());
        this.setProfileUrl(user.getProfileUrl());
        this.setAvatar(user.getAvatar());
        this.setAvatarMedium(user.getAvatarMedium());
        this.setAvatarFull(user.getAvatarFull());
        this.setCommunityVisibilityState(user.getCommunityVisibilityState());
        this.setProfileState(user.getProfileState());
        this.setTimeCreated(user.getTimeCreated());
    }
}