package jwom.fintrak.Auth;

import jakarta.persistence.EntityExistsException;
import jwom.fintrak.DTO.ApiResponse;
import jwom.fintrak.DTO.UserDTO;
import jwom.fintrak.DTO.request.AuthenticationRequest;
import jwom.fintrak.DTO.request.RegisterRequest;
import jwom.fintrak.DTO.response.AuthenticationResponse;
import jwom.fintrak.Data.UserRepository;
import jwom.fintrak.Model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public ApiResponse register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EntityExistsException("email already in use");
        }

        var user = User.builder()
                .email(request.getEmail())
                .password(encoder.encode(request.getPassword()))
                .role(User.Role.USER)
                .build();

        userRepository.save(user);

        var userDTO = UserDTO.builder().id(user.getId()).email(user.getEmail()).role(user.getRole()).build();
        var jwt = jwtService.generateToken(user);
        return ApiResponse.<AuthenticationResponse>builder()
                .status("created")
                .statusCode(201)
                .message("User registered successfully")
                .timestamp(System.currentTimeMillis())
                .data(AuthenticationResponse.builder().token(jwt).user(userDTO).build())
                .build();
    }

    public ApiResponse authenticate(AuthenticationRequest request) {
        var authObject = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = (User) authObject.getPrincipal();

        var userDTO = UserDTO.builder()
                .role(user.getRole())
                .accounts(user.getAccounts())
                .id(user.getId())
                .email(user.getEmail())
                .build();

        var jwt = jwtService.generateToken(user);

        return ApiResponse.<AuthenticationResponse>builder()
                .status("success")
                .statusCode(200)
                .message("User authenticated successfully")
                .timestamp(System.currentTimeMillis())
                .data(AuthenticationResponse.builder().token(jwt).user(userDTO).build())
                .build();
    }

}
