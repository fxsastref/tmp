package daw.pi.platinum.dto.platinum.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {

    // Attributes
    @Schema(description = "Username for registration", example = "user")
    @NotBlank(message = "Username is required")
    @Size(min = 2, max = 32, message = "Username must be between 2 and 32 characters")
    private String username;

    @Schema(description = "Password for the user", example = "user123")
    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 64, message = "Password must be between 8 and 64 characters")
    private String password;

    @Schema(description = "Email for the user", example = "user@gmail.com")
    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    @Size(max = 64, message = "Email too long")
    private String email;
}
