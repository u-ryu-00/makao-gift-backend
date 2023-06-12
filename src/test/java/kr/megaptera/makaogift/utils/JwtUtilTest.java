package kr.megaptera.makaogift.utils;

import com.auth0.jwt.exceptions.JWTDecodeException;
import kr.megaptera.makaogift.models.UserId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class JwtUtilTest {
    static final String SECRET = "SECRET";

    JwtUtil jwtUtil;

    @BeforeEach
    void setUp() {
        jwtUtil = new JwtUtil(SECRET);
    }

    @Test
    void encodeAndDecode() {
        UserId original = new UserId("a111");

        String token = jwtUtil.encode(original);

        assertThat(token).contains(".");

        UserId userId = jwtUtil.decode(token);

        assertThat(userId).isEqualTo(original);
    }

    @Test
    void decodeError() {
        assertThrows(JWTDecodeException.class, () -> {
            jwtUtil.decode("xxx");
        });
    }
}
