package jwom.fintrak.Auth;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jwom.fintrak.DTO.ApiError;
import jwom.fintrak.DTO.ApiResponse;
import jwom.fintrak.DTO.ApiValidationError;
import jwom.fintrak.DTO.request.AuthenticationRequest;
import jwom.fintrak.DTO.request.RegisterRequest;
import jwom.fintrak.DTO.response.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Tag(name = "Auth", description = "Authentication Endpoints")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    @Operation(summary = "creates a user")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "created"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Validation Error", content = @Content(schema = @Schema(implementation = ApiValidationError.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "409", description = "Email already in use", content = @Content(schema = @Schema(implementation = ApiError.class))),

    })
    public ResponseEntity<ApiResponse<AuthenticationResponse>> register(@Valid @RequestBody RegisterRequest request) {
        ApiResponse<AuthenticationResponse> response = authService.register(request);
        return ResponseEntity.status(201).body(response);
    }

    @PostMapping("/authenticate")
    @Operation(summary = "authenticates a user")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Success"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Invalid Credentials", content = @Content),
    })
    public ResponseEntity<ApiResponse<AuthenticationResponse>> Authenticate(@RequestBody @Valid AuthenticationRequest request) {
        ApiResponse<AuthenticationResponse> response = authService.authenticate(request);
        return ResponseEntity.ok(response);
    }

}

