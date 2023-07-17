package jwom.fintrak.Controllers.Response;

import jwom.fintrak.Model.Transaction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateTransactionResponse {

    private long id;

    private String description;

    private Double amount;

    private LocalDate date;

    private Transaction.Type type;

    private Long account_id;
}
