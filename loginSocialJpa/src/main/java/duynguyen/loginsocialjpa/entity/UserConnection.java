package duynguyen.loginsocialjpa.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "user_connections")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserConnection implements Serializable {
    @Id
    @Column(name = "user_id", length = 255, nullable = false)
    private String userId;

    @Id
    @Column(name = "provider_id", length = 255, nullable = false)
    private String providerId;

    @Id
    @Column(name = "provider_user_id", length = 255, nullable = false)
    private String providerUserId;

    @Column(name = "rank", nullable = false)
    private int rank;

    @Column(name = "display_name", length = 255, nullable = true)
    private String displayName;

    @Column(name = "profile_url", length = 512, nullable = true)
    private String profileUrl;

    @Column(name = "image_url", length = 512, nullable = true)
    private String imageUrl;

    @Column(name = "access_token", length = 512, nullable = true)
    private String accessToken;

    @Column(name = "secret", length = 512, nullable = true)
    private String secret;

    @Column(name = "refresh_token", length = 512, nullable = true)
    private String refreshToken;

    @Column(name = "expire_time", nullable = true)
    private Long expireTime;
}
