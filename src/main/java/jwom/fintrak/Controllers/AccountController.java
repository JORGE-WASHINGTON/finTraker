package jwom.fintrak.Controllers;

import jwom.fintrak.Controllers.Params.CreateAccountParams;
import jwom.fintrak.Data.AccountRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountRepository accountRepository;

    public AccountController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @PostMapping
    ResponseEntity<CreateAccountParams> createAccount(@RequestBody CreateAccountParams createAccountParams) {
        return ResponseEntity.ok(createAccountParams);
    }


}