package duynguyen.loginfacebook.model;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Facebook {
    private String userId;
    private String username;
    private String password;
    private String lastName;
    private String firstName;
    private String email;
}
