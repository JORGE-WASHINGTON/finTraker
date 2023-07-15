package jwom.fintrak.Auth.Request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    @Email @NotNull private String email;

    @NotNull @Size(min = 6)
    private String password;

    @NotNull
    private String name;
}
