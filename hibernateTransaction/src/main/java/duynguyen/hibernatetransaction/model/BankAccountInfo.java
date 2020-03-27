package duynguyen.hibernatetransaction.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BankAccountInfo {
    private int id;
    private String full_name;
    private double balance;
}
