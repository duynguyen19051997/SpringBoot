package duynguyen.hibernatetransaction.entity;

import lombok.*;
import org.springframework.data.annotation.Id;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bank_account")
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "full_name", nullable = false, length = 128)
    private String full_name;

    @Column(name = "balance", nullable = false)
    private double balance;
}
