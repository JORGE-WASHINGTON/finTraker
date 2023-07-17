package jwom.fintrak.Controllers.Response;

import jwom.fintrak.Model.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateAccountResponse {
    private Long id;
    private String title;
    private Account.Type accountType;
    private Double balance;
}
