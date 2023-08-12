package jwom.fintrak.DTO.request;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jwom.fintrak.DTO.Validation.ValueOfEnum;
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

    @NotNull
    private String title;
    @NotNull
    @Digits(integer = 10, fraction = 2)
    private Double balance;
    @NotNull
    @ValueOfEnum(enumClass = Account.Type.class)
    private String type;
}
