package jwom.fintrak.Controllers.Request;

import jwom.fintrak.Model.Transaction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateTransactionRequest {

    private String description;

    private Double amount;

    private LocalDate date;

    private Transaction.Type type;

    private Long account_id;

}
