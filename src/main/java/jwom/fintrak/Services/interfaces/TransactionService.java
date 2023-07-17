package jwom.fintrak.Services.interfaces;

import jwom.fintrak.Controllers.Request.CreateTransactionRequest;
import jwom.fintrak.Controllers.Response.CreateTransactionResponse;
import jwom.fintrak.Model.User;

public interface TransactionService {
    CreateTransactionResponse createTransaction(CreateTransactionRequest request);
}
