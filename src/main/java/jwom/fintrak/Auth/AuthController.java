package jwom.fintrak.Auth;

import jakarta.validation.Valid;
import jwom.fintrak.Auth.DTO.UserDTO;
import jwom.fintrak.Auth.Params.UserLoginParams;
import jwom.fintrak.Auth.Params.UserRegistrationParams;
import jwom.fintrak.Model.User;
import jwom.fintrak.Services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@RequestBody @Valid UserRegistrationParams userRegistrationParams) {
        User newUser = new User();
        newUser.mapFromParams(userRegistrationParams);
        User createdUser = userService.createUser(newUser);
        UserDTO userDto = new UserDTO();
        userDto.mapFromUser(createdUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDto);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody UserLoginParams userLoginParams) {
        // Validate request and authenticate the user
        // Implement your login logic here
        // Return appropriate response based on the authentication result

        return ResponseEntity.ok("User logged in successfully");
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logoutUser() {
        // Implement your logout logic here
        // Clear session or authentication tokens, if applicable

        return ResponseEntity.ok("User logged out successfully");
    }
}

