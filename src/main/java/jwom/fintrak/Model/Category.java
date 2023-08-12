package jwom.fintrak.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private Double budgetedAmount;

    private Double spentAmount;

    private Double remainingAmount;

    private Long categoryGroupId;

    @OneToMany
    @JoinColumn(name = "categoryId")
    private List<Transaction> transactions;
    public Category(String name) {
        this.name = name;
    }

}
