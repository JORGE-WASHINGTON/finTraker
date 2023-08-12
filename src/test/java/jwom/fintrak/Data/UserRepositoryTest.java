package jwom.fintrak.Data;

import jwom.fintrak.Model.Account;
import jwom.fintrak.Model.User;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@Slf4j
@DataJpaTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

   /* @BeforeEach
    public void setUp() {
        user = User.builder()
                .firstName("test")
                .lastName("test")
                .email("test@email.com")
                .password("test")
                .role(User.Role.USER).build();

    }*/


    @Test
    void givenValidUserThenSaveUserSuccessfully() {
        //given
        var user = User.builder()
                .firstName("test")
                .lastName("test")
                .email("test@email.com")
                .password("test")
                .role(User.Role.USER).build();

        //when
        var savedUser = userRepository.save(user);

        //then
        assertNotNull(savedUser);
        assertEquals(user, savedUser);
        log.info("Saved user: {}", savedUser);
    }

    @Test
    void TestUserAccountRelationship() {
        //given
        var user = User.builder()
                .firstName("test")
                .lastName("test")
                .email("test@email.com")
                .accounts(new ArrayList<Account>())
                .password("test")
                .role(User.Role.USER).build();

        userRepository.save(user);

        var account = Account.builder()
                .title("test")
                .accountType(Account.Type.BANK)
                .balance(0.0)
                .ownerId(user.getId()).build();


        user.getAccounts().add(account);
        userRepository.save(user);

        //then
        log.info("Saved user: {}", user);
        assertEquals(1, user.getAccounts().size());
        assertEquals(account.getOwnerId(), user.getId());
    }
}