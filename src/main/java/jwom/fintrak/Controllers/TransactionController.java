package jwom.fintrak.Controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jwom.fintrak.Controllers.Request.CreateTransactionRequest;
import jwom.fintrak.Model.User;
import jwom.fintrak.Services.TransactionServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
@Slf4j

public class TransactionController {

    private final TransactionServiceImpl transactionService;

    @PostMapping
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    void create(@RequestBody CreateTransactionRequest request, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        LocalDate date = request.getDate();
        log.info(date.toString());
    }

}
