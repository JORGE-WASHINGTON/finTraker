package jwom.fintrak.Controllers.Request;

import jwom.fintrak.Model.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateAccountRequest {
    private String title;
    private Double balance;
    private Account.Type type;
}
