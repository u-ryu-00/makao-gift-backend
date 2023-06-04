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
        jdbcTemplate.execute("DELETE FROM account");

        jdbcTemplate.execute("" +
                "INSERT INTO account(id, user_Id, amount)" +
                " VALUES(1, 'a111', 50000)"
        );

        jdbcTemplate.execute("" +
                "INSERT INTO account(id, user_Id, amount)" +
                " VALUES(2, 'b222', 50000)"
        );

        return "OK";
    }
}
