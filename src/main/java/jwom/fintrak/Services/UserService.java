package jwom.fintrak.Services;

import jwom.fintrak.Data.UserRepository;
import jwom.fintrak.Model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

}
