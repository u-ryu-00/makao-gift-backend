package kr.megaptera.makaogift.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import kr.megaptera.makaogift.models.UserId;

public class JwtUtil {
    private final Algorithm algorithm;

    public JwtUtil(String secret) {
        this.algorithm = Algorithm.HMAC256(secret);
    }

    public String encode(UserId userId) {
        return JWT.create()
                .withClaim("userId", userId.value())
                .sign(algorithm);
    }

    public UserId decode(String token) {
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT verify = verifier.verify(token);
        String value = verify.getClaim("userId").asString();
        return new UserId(value);
    }
}
