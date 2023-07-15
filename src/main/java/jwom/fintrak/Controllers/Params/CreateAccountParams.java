package jwom.fintrak.Controllers.Params;

import lombok.Data;

@Data
public class CreateAccountParams {

    private String title;
    private String accountType;
    private Double balance;
}
