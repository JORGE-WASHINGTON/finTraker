package jwom.fintrak.Auth;

import jwom.fintrak.Auth.Request.AuthenticateRequest;
import jwom.fintrak.Auth.Request.RegisterRequest;
import jwom.fintrak.Auth.Response.AuthenticationResponse;
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

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(request.getPassword())
                .role(User.Role.USER)
                .build();
        userRepository.save(user);
        var jwt = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwt).build();
    }

    public AuthenticationResponse authenticate(AuthenticateRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        var jwt = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwt).build();
    }

}
