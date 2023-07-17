package jwom.fintrak.Data;

import jwom.fintrak.Model.Account;
import jwom.fintrak.Model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    private User user;
    private Account account1;
    private Account account2;

    @BeforeEach
    public void setUp() {
        user = User.builder()
                .email("test@example.com")
                .name("Test user")
                .role(User.Role.USER)
                .password("password")
                .accounts(new ArrayList<>())
                .build();


        account1 = Account.builder()
                .accountType(Account.Type.CASH)
                .title("account 1")
                .user(user)
                .balance(1.00)
                .transactions(null)
                .build();

        account2 = Account.builder()
                .accountType(Account.Type.CASH)
                .title("account 2")
                .user(user)
                .balance(1.00)
                .transactions(null)
                .build();

        user.getAccounts().add(account1);
        user.getAccounts().add(account2);
        userRepository.save(user);
    }


    @Test
    void givenValidUserThenSaveUserSuccessfully() {
        //given
        User user = User.builder().email("user@email.com").name("test").role(User.Role.USER).password("test").accounts(null).build();
        //when
        User createdUser = userRepository.save(user);
        //then
        assertNotNull(createdUser);
        assertEquals(createdUser, user);
    }

    //relationship test
    @Test
    public void givenUserWithAccountsSaveUserAndAccounts() {
        // No given or when part, as they are performed in @BeforeEach

        // then
        assertNotNull(user.getId());
        assertNotNull(account1.getId());
        assertNotNull(account2.getId());
    }

    //relationship test
    @Test
    public void DeletingUserWithAccountsDeletesAccounts() {
        //given on setup

        //when
        userRepository.deleteById(user.getId());

        //then
        assertEquals(Optional.empty(), userRepository.findById(user.getId()));
        assertTrue(accountRepository.findAll().isEmpty());

    }

    //relationship test
    @Test
    public void givenExistingEmailReturnsUserWithAccounts() {
        //given on setup

        //when
        Optional<User> optionalUser = userRepository.findByEmail(user.getEmail());

        //then
        assertTrue(optionalUser.isPresent());
        User user = optionalUser.get();
        assertEquals(2, user.getAccounts().size());
    }

    @Test
    void givenNullUserThrowsInvalidDataAccessException() {
        //given
        User user = null;

        //when and then
        assertThrows(InvalidDataAccessApiUsageException.class, () -> {
                userRepository.save(user);
        });
    }

    @Test
    void givenExistingEmailReturnsMatchingUser() {
        //when
        Optional<User> optionalUser = userRepository.findByEmail("test@example.com");

        assertTrue(optionalUser.isPresent());
        User foundUser = optionalUser.get();
        assertEquals("Test user", foundUser.getName());
    }

    @Test
    void givenNonExistingEmailReturnsEmptyOptional() {
        //when
        Optional<User> optionalUser = userRepository.findByEmail("test1@example.com");

        //then
        assertTrue(optionalUser.isEmpty());
    }

    @Test
    public void testEmailNotNullConstraint() {
        User user = User.builder()
                .email(null)  // Invalid: Email is null
                .name("Test User")
                .role(User.Role.USER)
                .password("password")
                .build();

        assertThrows(DataIntegrityViolationException.class, () -> userRepository.save(user));
    }

    @Test
    public void testUniqueConstraintOnEmail() {

        // when and then
        User duplicateUser = User.builder()
                .email("test@example.com")  // Same email as setup user
                .name("Duplicate User")
                .role(User.Role.USER)
                .password("password")
                .build();

        assertThrows(DataIntegrityViolationException.class, () -> userRepository.save(duplicateUser));
    }

    @Test
    public void testNameNotNullConstraint() {
        User user = User.builder()
                .email("test@example.com")
                .name(null)  // Invalid: Name is null
                .role(User.Role.USER)
                .password("password")
                .build();

        assertThrows(DataIntegrityViolationException.class, () -> userRepository.save(user));
    }

    @Test
    public void testRoleNotNullConstraint() {
        User user = User.builder()
                .email("test@example.com")
                .name("Test User")
                .role(null)  // Invalid: Role is null
                .password("password")
                .build();

        assertThrows(DataIntegrityViolationException.class, () -> userRepository.save(user));
    }

    @Test
    public void testPasswordNotNullConstraint() {
        User user = User.builder()
                .email("test@example.com")
                .name("Test User")
                .role(User.Role.USER)
                .password(null)  // Invalid: Password is null
                .build();

        assertThrows(DataIntegrityViolationException.class, () -> userRepository.save(user));
    }
}