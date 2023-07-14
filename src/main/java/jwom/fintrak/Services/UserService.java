package jwom.fintrak.Services;

import jwom.fintrak.Auth.PasswordEncoderService;
import jwom.fintrak.Data.UserRepository;
import jwom.fintrak.Model.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoderService passwordEncoder;


    public UserService(UserRepository userRepository, PasswordEncoderService passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public User createUser(User newUser) {
        newUser.setPassword(passwordEncoder.encodePassword(newUser.getPassword()));
        return userRepository.save(newUser);
    }
}
