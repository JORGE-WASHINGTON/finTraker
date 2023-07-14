package jwom.fintrak.Auth.Params;

import lombok.Data;

@Data
public class UserLoginParams {
    private String email;
    private String password;
}
