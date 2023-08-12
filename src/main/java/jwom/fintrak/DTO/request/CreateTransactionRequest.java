package jwom.fintrak.DTO.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
public class CreateTransactionRequest {

    private String description;

    @NotNull
    @Positive
    private Double amount;

    @NotNull
    private LocalDate date;

    @NotNull
    private Transaction.Type type;

    @NotNull
    private Long account_id;

    @NotNull
    private Long user_id;

    private String category;

}
