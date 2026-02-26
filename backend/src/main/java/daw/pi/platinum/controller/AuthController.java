package daw.pi.platinum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException.BadRequest;

import daw.pi.platinum.dto.platinum.MessageResponse;
import daw.pi.platinum.dto.platinum.auth.LoginRequest;
import daw.pi.platinum.dto.platinum.auth.LoginResponse;
import daw.pi.platinum.dto.platinum.auth.RegisterRequest;
import daw.pi.platinum.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
@Tag(name = "Auth", description = "Login, register and linking to a steam account management")
public class AuthController {

    // Dependency injection
    @Autowired
    private AuthService authService;

    // Register endpoint
    @Operation(summary = "Register", description = "Add a new user to the database with its username, password and email as reference")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "User created successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MessageResponse.class))),
        @ApiResponse(responseCode = "400", description = "Invalid register data", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BadRequest.class))),
        @ApiResponse(responseCode = "409", description = "User already registered with same information", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BadRequest.class)))
    })
    @PostMapping("/register") // User registration
    public ResponseEntity<MessageResponse> register(@Valid @RequestBody RegisterRequest request) {
        // Service Call
        MessageResponse response = authService.register(request);

        // HTTP Response
        return ResponseEntity.ok(response);
    }

    // Login endpoint
    @Operation(summary = "Login", description = "Login a user comparing the introduced credentials with the ones of a registered user")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Login successful", content = @Content(mediaType = "application/json", schema = @Schema(implementation = LoginResponse.class))),
        @ApiResponse(responseCode = "400", description = "Username or password incorrect", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BadRequest.class))),
        @ApiResponse(responseCode = "404", description = "User not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping("/login") // User login
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        // Service Call
        LoginResponse response = authService.login(request);

        // HTTP Response
        return ResponseEntity.ok(response);
    }

    // Linking endpoint
    @Operation(summary = "Link steam account", description = "Link the current logged user to a steam account via steam_id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Linking successful", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MessageResponse.class))),
        @ApiResponse(responseCode = "404", description = "User or steam_id not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PutMapping("/linking") // User steam account linking
    public ResponseEntity<MessageResponse> linkSteamAccount(@Valid @RequestBody String request) {
        //Service Call
        MessageResponse response = authService.linkSteamAccount(request);

        // HTTP Response
        return ResponseEntity.ok(response);
    }
}
