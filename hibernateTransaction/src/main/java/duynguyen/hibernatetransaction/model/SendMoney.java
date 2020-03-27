package duynguyen.hibernatetransaction.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class SendMoney {
    private int fromId;
    private int toId;
    private double amount;
}
