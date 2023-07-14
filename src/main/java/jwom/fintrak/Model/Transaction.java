package jwom.fintrak.Model;

import lombok.Data;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private Double amount;

    private Date date;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    // Add any additional fields, relationships, and Lombok annotations as needed
}

