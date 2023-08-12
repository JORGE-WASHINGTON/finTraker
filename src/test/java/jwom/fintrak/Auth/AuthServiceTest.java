package jwom.fintrak.Auth;

import jakarta.persistence.EntityExistsException;
import jwom.fintrak.DTO.ApiResponse;
import jwom.fintrak.DTO.request.AuthenticationRequest;
import jwom.fintrak.DTO.request.RegisterRequest;
import jwom.fintrak.DTO.response.AuthenticationResponse;
import jwom.fintrak.Data.UserRepository;
import jwom.fintrak.Model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.NoMoreInteractions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
class AuthServiceTest {

    @Mock
    private PasswordEncoder encoder;

    @Mock
    private JwtService jwtService;

    @Mock
    AuthenticationManager authenticationManager;

    @Mock
    UserRepository userRepository;

    @InjectMocks
    private AuthService authService;

    @BeforeEach
    void setUp() {
        when(userRepository.save(Mockito.any(User.class))).thenAnswer(i -> i.getArgument(0));
    }

    @Test
    void register_ValidUser_ReturnsApiResponseWithUserAndToken() {
        //Arrange
        when(userRepository.existsByEmail(anyString())).thenReturn(false);
        when(jwtService.generateToken(Mockito.any(User.class))).thenReturn("jwt token");
        when(encoder.encode(anyString())).thenReturn("encoded password");
        var request = RegisterRequest.builder().name("test user").email("validemail@gmail.com").password("password").build();

        //Act
        ApiResponse<AuthenticationResponse> response = authService.register(request);

        //assertions
        assertEquals(201, response.getStatusCode());
        assertEquals("created", response.getStatus());
        assertEquals("User registered successfully", response.getMessage());

        var responseData = response.getData();
        assertNotNull(responseData);
        assertEquals("test user", responseData.getUser().getName());
        assertEquals("validemail@gmail.com", responseData.getUser().getEmail());
        assertEquals("jwt token", responseData.getToken());

        verify(userRepository).save(Mockito.any(User.class));
        verify(userRepository).existsByEmail(anyString());
        verify(jwtService).generateToken(Mockito.any(User.class));
    }

    @Test
    void register_ExistingUser_ThrowsEntityExistsException() {
        //Arrange
        when(userRepository.existsByEmail(anyString())).thenReturn(true);
        var request = RegisterRequest.builder().name("test user").email("existingEmail@gmail.com").password("password").build();

        //Act
        var exception = assertThrows(EntityExistsException.class, () -> authService.register(request));

        //assertions
        assertEquals("email already in use", exception.getMessage());
    }

    @Test
    void Authenticate_ValidUser_ReturnsApiResponseWithUserAndToken() {
        //Arrange
        Authentication authenticationMock = mock(Authentication.class);
        when(authenticationManager.authenticate(Mockito.any(UsernamePasswordAuthenticationToken.class))).thenReturn(authenticationMock);
        when(authenticationMock.getPrincipal()).thenReturn(any(User.class));
       // when(jwtService.generateToken(any(User.class))).thenReturn("jwt token");
        var request = AuthenticationRequest.builder().email("testemail").password("password").build();

        //Act
        ApiResponse<AuthenticationResponse> response = authService.authenticate(request);

        //assertions
        assertEquals(200, response.getStatusCode());
        assertEquals("success", response.getStatus());
        assertEquals("User authenticated successfully", response.getMessage());

        var responseData = response.getData();
        assertNotNull(responseData);
        assertEquals("jwt token", responseData.getToken());
        assertNotNull(responseData.getUser());
    }
}