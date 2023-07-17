package jwom.fintrak.Services;

import jwom.fintrak.Controllers.Request.CreateTransactionRequest;
import jwom.fintrak.Controllers.Response.CreateTransactionResponse;
import jwom.fintrak.Data.TransactionRepository;
import jwom.fintrak.Model.Transaction;
import jwom.fintrak.Model.User;
import jwom.fintrak.Services.interfaces.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    @Override
    public CreateTransactionResponse createTransaction(CreateTransactionRequest request) {
        var newTransaction = Transaction.builder().type(request.getType()).accountId(request.getAccount_id()).date(request.getDate()).amount(request.getAmount()).description(request.getDescription()).build();
        var createdTransaction = transactionRepository.save(newTransaction);
        return CreateTransactionResponse.builder().id(createdTransaction.getId()).account_id(createdTransaction.getAccountId()).type(createdTransaction.getType()).amount(createdTransaction.getAmount()).date(createdTransaction.getDate()).description(createdTransaction.getDescription()).build();

    }
}
