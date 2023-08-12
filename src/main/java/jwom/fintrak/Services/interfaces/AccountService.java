package jwom.fintrak.Services.interfaces;

import jwom.fintrak.DTO.ApiResponse;
import jwom.fintrak.DTO.request.CreateAccountRequest;
import jwom.fintrak.DTO.response.CreateAccountResponse;
import jwom.fintrak.DTO.response.RetrieveAccountResponse;
import jwom.fintrak.Model.Account;
import jwom.fintrak.Model.User;

import java.util.List;

public interface AccountService {
    ApiResponse<CreateAccountResponse> create(CreateAccountRequest request, Long userId);

    ApiResponse<List<Account>> getAccounts(User user);

    ApiResponse delete(Long AccountId, User user);

    ApiResponse<RetrieveAccountResponse> getAccountById(User user, Long accountId);
}