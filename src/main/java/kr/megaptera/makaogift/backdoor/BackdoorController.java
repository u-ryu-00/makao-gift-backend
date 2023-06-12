package kr.megaptera.makaogift.backdoor;

import jakarta.transaction.Transactional;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("backdoor")
@Transactional
public class BackdoorController {
    private final JdbcTemplate jdbcTemplate;

    private final PasswordEncoder passwordEncoder;

    public BackdoorController(JdbcTemplate jdbcTemplate,
                              PasswordEncoder passwordEncoder) {
        this.jdbcTemplate = jdbcTemplate;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("setup-database")
    public String setupDatabase() {
        jdbcTemplate.execute("DELETE FROM product");
        jdbcTemplate.execute("DELETE FROM account");
        jdbcTemplate.execute("DELETE FROM ORDER_HISTORY");

        jdbcTemplate.execute("" +
                "INSERT INTO account(id, user_Id, amount)" +
                " VALUES(1, 'a111', 50000)"
        );
        jdbcTemplate.execute("" +
                "INSERT INTO account(id, user_Id, amount)" +
                " VALUES(2, 'b222', 50000)"
        );

//        jdbcTemplate.update("" +
//                        "INSERT INTO account(" +
//                        " id, user_Id, name, amount, encoded_password" +
//                        ")" +
//                        " VALUES(1, 'a111', '내이름', 50000, ?)",
//                passwordEncoder.encode("Aa1!!!!!")
//        );


//        jdbcTemplate.update("" +
//                        "INSERT INTO account(" +
//                        " id, user_Id, name, amount, encoded_password" +
//                        ")" +
//                        " VALUES(2, 'b222', '네이름', 50000, ?)",
//                passwordEncoder.encode("Aa1!!!!!")
//        );

        jdbcTemplate.execute("" +
                "INSERT INTO product(product_Id, company, description, price, title, image_Url)" +
                " VALUES(1, '입생로랑', '투명하게 녹아 맑게 빛나는 컬러 글로우 밤', 2000, '[단독각인] 캔디 글레이즈 컬러밤', 'https://img1.kakaocdn.net/thumb/C320x320@2x.fwebp.q82/?fname=https%3A%2F%2Fst.kakaocdn.net%2Fproduct%2Fgift%2Fproduct%2F20230516143408_834e86474204499b9fb85a2a3911ddfa.jpg')"
        );

        jdbcTemplate.execute("" +
                "INSERT INTO product(product_Id, company, description, price, title, image_Url)" +
                " VALUES(2, '탬버린즈', '물에 닿는 순간 풍성한 거품으로 변해 피부를 부드럽게 씻어내어 자연의 향으로 감싸줍니다', 1000, '[선물포장] 바디워시 (4종 택1)', 'https://img1.kakaocdn.net/thumb/C320x320@2x.fwebp.q82/?fname=https%3A%2F%2Fst.kakaocdn.net%2Fproduct%2Fgift%2Fproduct%2F20221124180904_d7730ce8710a45a084a62ee6c1f56766.jpg')"
        );

        jdbcTemplate.execute("" +
                "INSERT INTO product(product_Id, company, description, price, title, image_Url)" +
                " VALUES(100, '헤라', '정교하게 피부 빈틈을 채우는 레이어리스 매트 커버가 내 피부처럼 가볍게 핏팅되어 벨벳티 스킨을 선사하는 세미 매트 쿠션입니다', 56000, '[단독] 헤라 블랙쿠션 27호(+악세서리함 추가 증정)', 'https://img1.kakaocdn.net/thumb/C320x320@2x.fwebp.q82/?fname=https%3A%2F%2Fst.kakaocdn.net%2Fproduct%2Fgift%2Fproduct%2F20230525133108_4d30acbb75504bc6830d2eda5795e99b.jpg')"
        );

        jdbcTemplate.execute("" +
                "INSERT INTO ORDER_HISTORY(order_Id, address, message, product_Id, quantity, receiver, total_Price, user_Id, title, company, description, image_Url, created_At)" +
                " VALUES(1, '성동구', '감사합니다', 1, 1, '유정', 2000, 'a111', '[단독각인] 캔디 글레이즈 컬러밤', '입생로랑', '투명하게 녹아 맑게 빛나는 컬러 글로우 밤', 'https://img1.kakaocdn.net/thumb/C320x320@2x.fwebp.q82/?fname=https%3A%2F%2Fst.kakaocdn.net%2Fproduct%2Fgift%2Fproduct%2F20230516143408_834e86474204499b9fb85a2a3911ddfa.jpg', '2023-06-10 13:40:40.044709')"
        );
        return "OK";
    }

    @GetMapping("delete-product")
    public String deleteProduct() {
        jdbcTemplate.execute("DELETE FROM product");

        return "OK";
    }

    @GetMapping("delete-order")
    public String deleteOrder() {
        jdbcTemplate.execute("DELETE FROM ORDER_HISTORY");

        return "OK";
    }
}
