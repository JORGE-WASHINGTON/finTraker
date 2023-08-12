package jwom.fintrak.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double amount;

    private String description;

    private Long categoryId;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private Long AccountId;

    @Column(nullable = false)
    private Type type;

    @ManyToOne
    @JoinColumn(name = "merchantId")
    private Merchant merchant;

    public enum Type {
        EXPENSE,
        INCOME
    }


}

