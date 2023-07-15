package jwom.fintrak.Auth;

import jakarta.validation.Valid;
import jwom.fintrak.Auth.Response.AuthenticationResponse;
import jwom.fintrak.Auth.Request.AuthenticateRequest;
import jwom.fintrak.Auth.Request.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody @Valid RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> Authenticate(@RequestBody AuthenticateRequest request) {

        return ResponseEntity.ok(authService.authenticate(request));
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logoutUser() {
        // Implement your logout logic here
        // Clear session or authentication tokens, if applicable

        return ResponseEntity.ok("User logged out successfully");
    }
}

