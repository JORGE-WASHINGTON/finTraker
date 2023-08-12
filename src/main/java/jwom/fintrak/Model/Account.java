package jwom.fintrak.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Type accountType;

    @Column(nullable = false)
    private Double balance;

    @Column(nullable = false, name = "user_id")
    private Long ownerId;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "accountId")
    private List<Transaction> transactions = new ArrayList<Transaction>();

    public void subtractBalance(Double amount) {
        balance = this.balance - amount;
    }

    public void addBalance(Double amount) {
        balance = this.balance + amount;
    }

    public enum Type {
        CASH,
        BANK,
        CREDIT_CARD
    }

}

