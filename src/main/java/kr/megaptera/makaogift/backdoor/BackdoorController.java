package kr.megaptera.makaogift.backdoor;

import jakarta.transaction.Transactional;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("backdoor")
@Transactional
public class BackdoorController {
    private final JdbcTemplate jdbcTemplate;

    public BackdoorController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("setup-database")
    public String setupDatabase() {
        jdbcTemplate.execute("DELETE FROM product");
        jdbcTemplate.execute("DELETE FROM account");

        jdbcTemplate.execute("" +
                "INSERT INTO account(id, user_Id, amount)" +
                " VALUES(1, 'a111', 50000)"
        );

        jdbcTemplate.execute("" +
                "INSERT INTO account(id, user_Id, amount)" +
                " VALUES(2, 'b222', 50000)"
        );

        jdbcTemplate.execute("" +
                "INSERT INTO product(id, company, description, price, title)" +
                " VALUES(1, '입생로랑', '투명하게 녹아 맑게 빛나는 컬러 글로우 밤', 49000, '[단독각인] 캔디 글레이즈 컬러밤')"
        );

        jdbcTemplate.execute("" +
                "INSERT INTO product(id, company, description, price, title)" +
                " VALUES(2, '탬버린즈', '물에 닿는 순간 풍성한 거품으로 변해 피부를 부드럽게 씻어내어 자연의 향으로 감싸줍니다', 33000, '[선물포장] 바디워시 (4종 택1)')"
        );

        return "OK";
    }

    @GetMapping("delete-product")
    public String deleteProduct() {
        jdbcTemplate.execute("DELETE FROM product");

        return "OK";
    }
}
