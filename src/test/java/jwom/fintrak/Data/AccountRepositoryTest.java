package jwom.fintrak.Data;

import jwom.fintrak.Model.Account;
import jwom.fintrak.Model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AccountRepositoryTest {

    @Autowired AccountRepository accountRepository;

    @Autowired UserRepository userRepository;

    private User nonPersisteduser;
    private User persistedUser;

    @BeforeEach
    public void setUp() {
        nonPersisteduser = User.builder()
                .email("nonpersisted@example.com")
                .name("nonpersisted")
                .role(User.Role.USER)
                .password("password")
                .accounts(null)
                .build();

        persistedUser = User.builder()
                .email("persisted@example.com")
                .name("persisted user")
                .role(User.Role.USER)
                .password("password")
                .accounts(null)
                .build();

        userRepository.save(persistedUser);

    }

    //relationship test
    @Test
    void givenAccountWithNotPersistedUserThrows() {
        //given
        Account account = Account.builder().accountType(Account.Type.CASH).title("account title").balance(0.00).transactions(null).user(nonPersisteduser).build();

        //when and then
        assertThrows(InvalidDataAccessApiUsageException.class, () -> {
            accountRepository.save(account);
        });
    }

    @Test
    void givenValidAccountSavesSuccessfully() {
        //given
        Account account = Account.builder().accountType(Account.Type.CASH).title("account title").balance(0.00).transactions(null).user(persistedUser).build();

        //when
        Account createdAccount = accountRepository.save(account);

        //then
        assertNotNull(createdAccount);
        assertEquals(createdAccount, account);
        assertEquals(createdAccount.getUser().getId(), persistedUser.getId());
    }

}