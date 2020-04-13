package duynguyen.loginsecutityjdbc.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class UserRoles {
    private int id;
    private int user_id;
    private int role_id;
}
