package duynguyen.transaction.model;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BankAccountInfor {
    private int id;
    private String fullName;
    private double balance;
}
