package jwom.fintrak.Controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jwom.fintrak.Controllers.Request.CreateAccountRequest;

import jwom.fintrak.Controllers.Response.CreateAccountResponse;
import jwom.fintrak.Controllers.Response.RetrieveAccountResponse;
import jwom.fintrak.Model.User;
import jwom.fintrak.Services.AccountServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
@Slf4j
public class AccountController {

    private final AccountServiceImpl accountService;

    @PostMapping
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    ResponseEntity<CreateAccountResponse> create(@RequestBody CreateAccountRequest request, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return ResponseEntity.status(HttpStatus.CREATED).body(accountService.create(request, user));
    }

    @GetMapping("/{accountId}")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    ResponseEntity<RetrieveAccountResponse> retrieve(@PathVariable Long accountId, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return ResponseEntity.ok(accountService.getAccountById(accountId, user));
    }


}