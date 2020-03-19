package duynguyen.jpatransaction.model;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SendMoney {
    private int fromId;
    private int toId;
    private double amount;
}
