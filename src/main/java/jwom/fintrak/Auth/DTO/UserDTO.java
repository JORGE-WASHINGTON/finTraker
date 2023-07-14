package jwom.fintrak.Auth.DTO;

import jwom.fintrak.Model.Account;
import jwom.fintrak.Model.User;
import lombok.Data;

import java.util.List;

@Data
public class UserDTO {
    private Long id;
    private String email;
    private String name;
    private List<Account> accounts;

    public void mapFromUser(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.accounts = user.getAccounts();

    }
}
