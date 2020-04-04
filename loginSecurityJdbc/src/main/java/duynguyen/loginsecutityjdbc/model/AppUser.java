package duynguyen.loginsecutityjdbc.model;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AppUser {
    private int id;
    private String username;
    private String password;
}
