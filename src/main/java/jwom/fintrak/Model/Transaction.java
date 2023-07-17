package jwom.fintrak.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private Double amount;

    private LocalDate date;

    private Type type;

    @Column(name = "account_id", insertable = false, updatable = false)
    private Long accountId;

    public enum Type {
        EXPENSE,
        INCOME
    }

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

}

