package daw.pi.platinum.dto.platinum.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {

    // Attributes
    @Schema(description = "JWT access token", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
    private String token;

    @Schema(description = "Token type", example = "Bearer")
    private String type = "Bearer";

    @Schema(description = "Autenticated username", example = "user")
    private String username;

    @Schema(description = "Email of the authenticated user", example = "user@gmail.com")
    private String email;

    @Schema(description = "Role of the authenticated user", example = "USER")
    private String role;
    private Boolean enabled;

    // Constructor
    public LoginResponse(String token, String username, String email, String role, Boolean enabled) {
        this.setToken(token);
        this.setUsername(username);
        this.setEmail(email);
        this.setRole(role);
        this.setEnabled(enabled);
    }
}
