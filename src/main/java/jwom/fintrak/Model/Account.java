package jwom.fintrak.Model;

import jakarta.persistence.*;
import jwom.fintrak.Controllers.Params.CreateAccountParams;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String accountType;

    private Double balance;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Transaction> transactions;

    public void mapFromParams(CreateAccountParams params) {
        this.title = params.getTitle();
        this.balance = params.getBalance();
        this.accountType = params.getAccountType();
    }

}

