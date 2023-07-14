package jwom.fintrak.Model;

import jakarta.persistence.*;

import jwom.fintrak.Auth.Params.UserRegistrationParams;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Account> accounts;

    public void mapFromParams(UserRegistrationParams params) {
        this.name = params.getName();
        this.email = params.getEmail();
        this.password = params.getPassword();
    }


}

