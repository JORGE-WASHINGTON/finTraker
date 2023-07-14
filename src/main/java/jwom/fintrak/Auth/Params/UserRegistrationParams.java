package jwom.fintrak.Auth.Params;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRegistrationParams {

    @Email @NotNull private String email;

    @NotNull @Size(min = 6)
    private String password;

    @NotNull
    private String name;
}
