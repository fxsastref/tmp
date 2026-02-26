package daw.pi.platinum.dto.platinum.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginRequest {
    // Attributes

    @Schema(description = "Username for login", example = "user")
    @NotBlank(message = "Username is required")
    @Size(min = 2, max = 32, message = "Username must be between 2 and 32 characters")
    private String username;

    @Schema(description = "User's password for the login", example = "user123")
    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 64, message = "Password must be between 8 and 64 characters")
    private String password;
}
