package auth;
import entity.User;
import io.smallrye.jwt.build.Jwt;

import java.time.Duration;
import java.time.Instant;
import java.util.Set;

public class TokenGenerator {

    public static String generateToken(String issuer, String upn,User.Role role) {
        return Jwt.issuer(issuer)
                .upn(upn)
                .groups(Set.of(role.name()))
                .expiresAt(Instant.now().plus(Duration.ofHours(1)))
                .sign();
    }
}
