package jwom.fintrak.DTO.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    @Email
    @NotNull
    private String email;

    @NotNull
    @Size(min = 6)
    private String password;

    @NotNull
    @Size(max = 50)
    private String name;
}
