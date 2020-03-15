package duynguyen.transaction.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SendMoneyForm {
    private int fromAccountId;
    private int toAccountId;
    private double amount;
}
