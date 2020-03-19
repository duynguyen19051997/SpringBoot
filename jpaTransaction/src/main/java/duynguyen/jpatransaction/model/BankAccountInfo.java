package duynguyen.jpatransaction.model;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BankAccountInfo {
    private int id;
    private String full_name;
    private double balance;
}
