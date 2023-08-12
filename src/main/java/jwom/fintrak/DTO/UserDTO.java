package jwom.fintrak.DTO;

import jwom.fintrak.Model.Account;
import jwom.fintrak.Model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private List<Account> accounts;
    private User.Role role;
}
