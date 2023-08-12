package jwom.fintrak.Services.interfaces;

import jwom.fintrak.DTO.ApiResponse;
import jwom.fintrak.DTO.request.CreateTransactionRequest;
import jwom.fintrak.DTO.response.CreateTransactionResponse;
import jwom.fintrak.Model.Transaction;
import jwom.fintrak.Model.User;

import java.util.Date;
import java.util.HashMap;

public interface TransactionService {
    ApiResponse<CreateTransactionResponse> createTransaction(CreateTransactionRequest request, User user);

    ApiResponse<Transaction> getTransactionById(Long id, User user);

    ApiResponse deleteTransactionById(Long transactionId, User user);

    void getTransactionSummary(User user);
}
