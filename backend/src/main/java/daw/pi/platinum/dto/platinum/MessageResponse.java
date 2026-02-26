package daw.pi.platinum.dto.platinum;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MessageResponse {

    // Attributes
    @Schema(description = "Response to the request to the Api", example = "Successful Api request cofirmation message")
    private String message;
}
