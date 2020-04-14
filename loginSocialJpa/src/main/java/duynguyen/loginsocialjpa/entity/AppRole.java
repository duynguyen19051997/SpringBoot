package duynguyen.loginsocialjpa.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "app_roles", uniqueConstraints = {
    @UniqueConstraint(name = "APP_ROLE_UK", columnNames = "role_name")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AppRole {
    public static final String ROLE_USER = "ROLE_USER";
    public static final String ROLE_ADMIN = "ROLE_ADMIN";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id", nullable = false)
    private long roleId;

    @Column(name = "role_name", length = 30, nullable = false)
    private String roleName;
}
