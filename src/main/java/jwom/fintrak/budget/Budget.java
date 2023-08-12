package jwom.fintrak.budget;

import jakarta.persistence.*;
import jwom.fintrak.Model.CategoryGroup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Budget {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Long userId;

    @OneToMany
    @JoinColumn(name = "budgetId")
    private List<CategoryGroup> categoryGroupList;
}
