package duynguyen.securityjwt.model;

import lombok.*;

import java.io.Serializable;


/*
 *This class is required for creating a response containing the JWT to be returned to the user.
 * */

@Getter
@Setter
@AllArgsConstructor
@ToString
public class JwtResponse implements Serializable {
    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwtToken;
}
