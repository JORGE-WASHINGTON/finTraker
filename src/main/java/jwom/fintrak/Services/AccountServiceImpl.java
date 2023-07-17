package jwom.fintrak.Services;

import jakarta.persistence.EntityNotFoundException;
import jwom.fintrak.Auth.JwtService;
import jwom.fintrak.Controllers.Request.CreateAccountRequest;
import jwom.fintrak.Controllers.Response.CreateAccountResponse;
import jwom.fintrak.Controllers.Response.RetrieveAccountResponse;
import jwom.fintrak.Data.AccountRepository;
import jwom.fintrak.Data.UserRepository;
import jwom.fintrak.Model.Account;
import jwom.fintrak.Model.User;
import jwom.fintrak.Services.interfaces.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

   public CreateAccountResponse create(CreateAccountRequest request, User user) {
       var account = Account.builder()
               .title(request.getTitle())
               .accountType(request.getType())
               .balance(request.getBalance())
               .user(user)
               .build();
       var savedAccount = accountRepository.save(account);
       return CreateAccountResponse.builder().id(savedAccount.getId()).accountType(savedAccount.getAccountType()).balance(savedAccount.getBalance()).title(savedAccount.getTitle()).build();
   }

    public RetrieveAccountResponse getAccountById(Long accountId, User user) {
        Account account = accountRepository.findById(accountId).orElseThrow();
        if(!Objects.equals(user.getId(), account.getUser().getId())) {
            //TODO
            //Implement ApiError
            throw new EntityNotFoundException();
        }
        return RetrieveAccountResponse.builder().id(account.getId()).balance(account.getBalance()).accountType(account.getAccountType()).title(account.getTitle()).build();
    }
}
