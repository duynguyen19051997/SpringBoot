package duynguyen.securityjwt.model;

import lombok.*;

import java.io.Serializable;

/*
 * This class is required for storing the username and password we received from the client.
 * */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class JwtRequest implements Serializable {
    private static final long serialVersionUID = 5926468583005150707L;
    private String username;
    private String password;
}
