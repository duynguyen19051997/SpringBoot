package duynguyen.loginsocialjpa.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "app_users",
    uniqueConstraints = {@UniqueConstraint(name = "APP_USER_UK", columnNames = "username"),
        @UniqueConstraint(name = "APP_USER_UK2", columnNames = "email")})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private long userId;

    @Column(name = "username", length = 36, nullable = false)
    private String username;

    @Column(name = "email", length = 128, nullable = false)
    private String email;

    @Column(name = "first_name", length = 36, nullable = true)
    private String firstName;

    @Column(name = "last_name", length = 36, nullable = true)
    private String lastName;

    @Column(name = "password", length = 128, nullable = false)
    private String password;

    @Column(name = "enabled", length = 1, nullable = false)
    private boolean enabled;
}

