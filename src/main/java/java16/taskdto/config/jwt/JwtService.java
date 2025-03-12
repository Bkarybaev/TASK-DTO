package java16.taskdto.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import java16.taskdto.entityes.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Component
public class JwtService {
    //secret key
    @Value("${spring.security.jwt.secret_key}")
    private  String SECRET_KEY;

    //generate token /  create token
    public String generateToken(User user){

        // header
        JWTCreator.Builder builder = JWT.create();

        // peoloat
        builder.withClaim("email", user.getEmail());
        builder.withClaim("id", user.getId());
        builder.withClaim("role",user.getRoleUser().name());

        Instant now = Instant.now();
        builder.withIssuedAt(now);
        builder.withExpiresAt(now.plus(1, ChronoUnit.MINUTES));

        // signatura
      return builder.sign(Algorithm.HMAC256(SECRET_KEY));


    }

}
