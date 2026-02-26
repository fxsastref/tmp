package daw.pi.platinum.dto.platinum.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class SteamAccountLinkRequest {
    @Schema(description = "Steam id", example = "12345678901234567")
    private String steamId;

    private String username;
}
