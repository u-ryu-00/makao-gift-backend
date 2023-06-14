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
                "INSERT INTO account(id, user_Id, amount, name, encoded_password)" +
                " VALUES(1, 'a111', 50000, '이름', '$argon2id$v=19$m=65536,t=10,p=2$Qt3xgQ/d9w6EA2e4FYpGCw$qSIAubIKvXKmxDag3jcMWaA+JtadtQnpzOk8ydzta5I')"
        );
        jdbcTemplate.execute("" +
                "INSERT INTO account(id, user_Id, amount, name, encoded_password)" +
                " VALUES(2, 'b222', 50000, '이름', '$argon2id$v=19$m=65536,t=10,p=2$Qt3xgQ/d9w6EA2e4FYpGCw$qSIAubIKvXKmxDag3jcMWaA+JtadtQnpzOk8ydzta5I')"
        );

        jdbcTemplate.execute("" +
                "INSERT INTO product(product_Id, company, description, price, title, image_Url)" +
                " VALUES(1, '입생로랑', '투명하게 녹아 맑게 빛나는 컬러 글로우 밤', 1000, '[단독각인] 캔디 글레이즈 컬러밤', 'https://img1.kakaocdn.net/thumb/C320x320@2x.fwebp.q82/?fname=https%3A%2F%2Fst.kakaocdn.net%2Fproduct%2Fgift%2Fproduct%2F20230516143408_834e86474204499b9fb85a2a3911ddfa.jpg')"
        );

        jdbcTemplate.execute("" +
                "INSERT INTO product(product_Id, company, description, price, title, image_Url)" +
                " VALUES(2, '탬버린즈', '물에 닿는 순간 풍성한 거품으로 변해 피부를 부드럽게 씻어내어 자연의 향으로 감싸줍니다', 2000, '[선물포장] 바디워시', 'https://img1.kakaocdn.net/thumb/C320x320@2x.fwebp.q82/?fname=https%3A%2F%2Fst.kakaocdn.net%2Fproduct%2Fgift%2Fproduct%2F20221124180904_d7730ce8710a45a084a62ee6c1f56766.jpg')"
        );

        jdbcTemplate.execute("" +
                "INSERT INTO product(product_Id, company, description, price, title, image_Url)" +
                " VALUES(100, '헤라', '정교하게 피부 빈틈을 채우는 레이어리스 매트 커버가 내 피부처럼 가볍게 핏팅되어 벨벳티 스킨을 선사하는 세미 매트 쿠션입니다', 56000, '[단독] 헤라 블랙쿠션 27호(+악세서리함 추가 증정)', 'https://img1.kakaocdn.net/thumb/C320x320@2x.fwebp.q82/?fname=https%3A%2F%2Fst.kakaocdn.net%2Fproduct%2Fgift%2Fproduct%2F20230525133108_4d30acbb75504bc6830d2eda5795e99b.jpg')"
        );

        jdbcTemplate.execute("" +
                "INSERT INTO product(product_Id, company, description, price, title, image_Url)" +
                " VALUES(3, '논픽션', '컴팩트한 사이즈로 가벼운 외출이나 여행 시 유용한 미니 사이즈의 오 드 퍼퓸입니다', 3000, '[단독/에코백증정] NEW 시트러스 오 드 퍼퓸 10ML & 파우치', 'https://img1.kakaocdn.net/thumb/C320x320@2x.fwebp.q82/?fname=https%3A%2F%2Fst.kakaocdn.net%2Fproduct%2Fgift%2Fproduct%2F20230612164601_243b37e2222541d8b6ac2bcb392160f9.jpg')"
        );

        jdbcTemplate.execute("" +
                "INSERT INTO product(product_Id, company, description, price, title, image_Url)" +
                " VALUES(4, '디올', '매력적인 꾸뛰르 디자인이 돋보이는 새로운 뷰티 에센셜, NEW 디올 르 밤 핸드 크림을 만나보세요', 4000, '[단독각인] NEW 디올 르 밤', 'https://img1.kakaocdn.net/thumb/C320x320@2x.fwebp.q82/?fname=https%3A%2F%2Fst.kakaocdn.net%2Fproduct%2Fgift%2Fproduct%2F20230425182131_cf83c743ef06448990a92df6a3ecb6f4.jpg')"
        );

        jdbcTemplate.execute("" +
                "INSERT INTO product(product_Id, company, description, price, title, image_Url)" +
                " VALUES(5, '휩드', '팩 + 클렌징 + 스킨케어를 한 번에 휩드 비건 팩클렌저', 5000, '[단독/선물세트] \"더현대서울 품절대란템\" 비건 팩클렌저 4종 디스커버리 키트', 'https://img1.kakaocdn.net/thumb/C320x320@2x.fwebp.q82/?fname=https%3A%2F%2Fst.kakaocdn.net%2Fproduct%2Fgift%2Fproduct%2F20230607154117_3f0f2439fe444bc8a8a5757948f5e3cd.png')"
        );

        jdbcTemplate.execute("" +
                "INSERT INTO product(product_Id, company, description, price, title, image_Url)" +
                " VALUES(6, '조말론런던', '풍부한 영양을 선사하는 바디 크림으로 당신의 데일리 루틴을 완성해 보세요.', 6000, '[선물포장] 바디 크림 50ML', 'https://img1.kakaocdn.net/thumb/C320x320@2x.fwebp.q82/?fname=https%3A%2F%2Fst.kakaocdn.net%2Fproduct%2Fgift%2Fproduct%2F20230531143238_72714119df824f1c84694bac333c61f7.jpg')"
        );

        jdbcTemplate.execute("" +
                "INSERT INTO product(product_Id, company, description, price, title, image_Url)" +
                " VALUES(7, '이솝', '지친 손과 큐티클에 풍부한 수분을 공급해 주는 향긋한 보태니컬 향으로 모두에게 사랑받는 핸드 밤', 7000, '[단독포장] 레저렉션 아로마틱 핸드 밤', 'https://img1.kakaocdn.net/thumb/C320x320@2x.fwebp.q82/?fname=https%3A%2F%2Fst.kakaocdn.net%2Fproduct%2Fgift%2Fproduct%2F20230414161656_dd4131109bfa43eb9d7be850c555ac5e.jpg')"
        );

        jdbcTemplate.execute("" +
                "INSERT INTO product(product_Id, company, description, price, title, image_Url)" +
                " VALUES(8, '러쉬', '감미로운 슬리피 레인지의 자장가가 당신을 꿈의 세계로 초대합니다', 8000, '\"달콤한 꿀잠선물\" 슬리피 - 기프트 (샤워젤+바디로션)/선물', 'https://img1.kakaocdn.net/thumb/C320x320@2x.fwebp.q82/?fname=https%3A%2F%2Fst.kakaocdn.net%2Fproduct%2Fgift%2Fproduct%2F20230414095957_a0fcad26f78b4bd1806064708bcc435c.jpg')"
        );

        jdbcTemplate.execute("" +
                "INSERT INTO product(product_Id, company, description, price, title, image_Url)" +
                " VALUES(9, '달바', '어떤 피부 타입도 프리미엄 비건으로 맞춤 케어 가능한 프리미엄 더블 세트를 소중한 분께 선물하세요', 9000, '[단독포장] 달바 프리미엄 미스트 세럼 60ml+더블 크림 70g 세트', 'https://img1.kakaocdn.net/thumb/C320x320@2x.fwebp.q82/?fname=https%3A%2F%2Fst.kakaocdn.net%2Fproduct%2Fgift%2Fproduct%2F20230110210727_46f63dad01d6424e8dc72ff5a0cdf686.jpg')"
        );

        jdbcTemplate.execute("" +
                "INSERT INTO product(product_Id, company, description, price, title, image_Url)" +
                " VALUES(10, '랑콤', 'UV 엑스퍼트는 피부에 얇고 고르게 밀착되며, SPF50+/PA++++의 강력한 자외선 차단 효과가 UVA, UVB로부터 피부를 보호해줍니다', 10000, '[19년연속1위] UV 엑스퍼트 30ml 세트 (+30ml 용량 추가 증정)', 'https://img1.kakaocdn.net/thumb/C320x320@2x.fwebp.q82/?fname=https%3A%2F%2Fst.kakaocdn.net%2Fproduct%2Fgift%2Fproduct%2F20230605153528_745fbf7d6bbf45b9b3118e4383dd1756.jpg')"
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

    @GetMapping("add-order")
    public String addOrder() {
        jdbcTemplate.execute("" +
                "INSERT INTO ORDER_HISTORY(order_Id, address, message, product_Id, quantity, receiver, total_Price, user_Id, title, company, description, image_Url, created_At)" +
                " VALUES(2, '성동구', '감사합니다', 1, 1, '유정2', 2000, 'a111', '[단독각인] 캔디 글레이즈 컬러밤', '입생로랑', '투명하게 녹아 맑게 빛나는 컬러 글로우 밤', 'https://img1.kakaocdn.net/thumb/C320x320@2x.fwebp.q82/?fname=https%3A%2F%2Fst.kakaocdn.net%2Fproduct%2Fgift%2Fproduct%2F20230516143408_834e86474204499b9fb85a2a3911ddfa.jpg', '2023-06-10 13:40:40.044709')"
        );

        jdbcTemplate.execute("" +
                "INSERT INTO ORDER_HISTORY(order_Id, address, message, product_Id, quantity, receiver, total_Price, user_Id, title, company, description, image_Url, created_At)" +
                " VALUES(3, '성동구', '감사합니다', 1, 1, '유정3', 2000, 'a111', '[단독각인] 캔디 글레이즈 컬러밤', '입생로랑', '투명하게 녹아 맑게 빛나는 컬러 글로우 밤', 'https://img1.kakaocdn.net/thumb/C320x320@2x.fwebp.q82/?fname=https%3A%2F%2Fst.kakaocdn.net%2Fproduct%2Fgift%2Fproduct%2F20230516143408_834e86474204499b9fb85a2a3911ddfa.jpg', '2023-06-10 13:40:40.044709')"
        );

        jdbcTemplate.execute("" +
                "INSERT INTO ORDER_HISTORY(order_Id, address, message, product_Id, quantity, receiver, total_Price, user_Id, title, company, description, image_Url, created_At)" +
                " VALUES(4, '성동구', '감사합니다', 1, 1, '유정4', 2000, 'a111', '[단독각인] 캔디 글레이즈 컬러밤', '입생로랑', '투명하게 녹아 맑게 빛나는 컬러 글로우 밤', 'https://img1.kakaocdn.net/thumb/C320x320@2x.fwebp.q82/?fname=https%3A%2F%2Fst.kakaocdn.net%2Fproduct%2Fgift%2Fproduct%2F20230516143408_834e86474204499b9fb85a2a3911ddfa.jpg', '2023-06-10 13:40:40.044709')"
        );

        jdbcTemplate.execute("" +
                "INSERT INTO ORDER_HISTORY(order_Id, address, message, product_Id, quantity, receiver, total_Price, user_Id, title, company, description, image_Url, created_At)" +
                " VALUES(5, '성동구', '감사합니다', 1, 1, '유정5', 2000, 'a111', '[단독각인] 캔디 글레이즈 컬러밤', '입생로랑', '투명하게 녹아 맑게 빛나는 컬러 글로우 밤', 'https://img1.kakaocdn.net/thumb/C320x320@2x.fwebp.q82/?fname=https%3A%2F%2Fst.kakaocdn.net%2Fproduct%2Fgift%2Fproduct%2F20230516143408_834e86474204499b9fb85a2a3911ddfa.jpg', '2023-06-10 13:40:40.044709')"
        );

        jdbcTemplate.execute("" +
                "INSERT INTO ORDER_HISTORY(order_Id, address, message, product_Id, quantity, receiver, total_Price, user_Id, title, company, description, image_Url, created_At)" +
                " VALUES(6, '성동구', '감사합니다', 1, 1, '유정6', 2000, 'a111', '[단독각인] 캔디 글레이즈 컬러밤', '입생로랑', '투명하게 녹아 맑게 빛나는 컬러 글로우 밤', 'https://img1.kakaocdn.net/thumb/C320x320@2x.fwebp.q82/?fname=https%3A%2F%2Fst.kakaocdn.net%2Fproduct%2Fgift%2Fproduct%2F20230516143408_834e86474204499b9fb85a2a3911ddfa.jpg', '2023-06-10 13:40:40.044709')"
        );

        jdbcTemplate.execute("" +
                "INSERT INTO ORDER_HISTORY(order_Id, address, message, product_Id, quantity, receiver, total_Price, user_Id, title, company, description, image_Url, created_At)" +
                " VALUES(7, '성동구', '감사합니다', 1, 1, '유정7', 2000, 'a111', '[단독각인] 캔디 글레이즈 컬러밤', '입생로랑', '투명하게 녹아 맑게 빛나는 컬러 글로우 밤', 'https://img1.kakaocdn.net/thumb/C320x320@2x.fwebp.q82/?fname=https%3A%2F%2Fst.kakaocdn.net%2Fproduct%2Fgift%2Fproduct%2F20230516143408_834e86474204499b9fb85a2a3911ddfa.jpg', '2023-06-10 13:40:40.044709')"
        );

        jdbcTemplate.execute("" +
                "INSERT INTO ORDER_HISTORY(order_Id, address, message, product_Id, quantity, receiver, total_Price, user_Id, title, company, description, image_Url, created_At)" +
                " VALUES(8, '성동구', '감사합니다', 1, 1, '유정8', 2000, 'a111', '[단독각인] 캔디 글레이즈 컬러밤', '입생로랑', '투명하게 녹아 맑게 빛나는 컬러 글로우 밤', 'https://img1.kakaocdn.net/thumb/C320x320@2x.fwebp.q82/?fname=https%3A%2F%2Fst.kakaocdn.net%2Fproduct%2Fgift%2Fproduct%2F20230516143408_834e86474204499b9fb85a2a3911ddfa.jpg', '2023-06-10 13:40:40.044709')"
        );

        jdbcTemplate.execute("" +
                "INSERT INTO ORDER_HISTORY(order_Id, address, message, product_Id, quantity, receiver, total_Price, user_Id, title, company, description, image_Url, created_At)" +
                " VALUES(9, '성동구', '감사합니다', 1, 1, '유정9', 2000, 'a111', '[단독각인] 캔디 글레이즈 컬러밤', '입생로랑', '투명하게 녹아 맑게 빛나는 컬러 글로우 밤', 'https://img1.kakaocdn.net/thumb/C320x320@2x.fwebp.q82/?fname=https%3A%2F%2Fst.kakaocdn.net%2Fproduct%2Fgift%2Fproduct%2F20230516143408_834e86474204499b9fb85a2a3911ddfa.jpg', '2023-06-10 13:40:40.044709')"
        );

        jdbcTemplate.execute("" +
                "INSERT INTO ORDER_HISTORY(order_Id, address, message, product_Id, quantity, receiver, total_Price, user_Id, title, company, description, image_Url, created_At)" +
                " VALUES(10, '성동구', '감사합니다', 1, 1, '유정10', 2000, 'a111', '[단독각인] 캔디 글레이즈 컬러밤', '입생로랑', '투명하게 녹아 맑게 빛나는 컬러 글로우 밤', 'https://img1.kakaocdn.net/thumb/C320x320@2x.fwebp.q82/?fname=https%3A%2F%2Fst.kakaocdn.net%2Fproduct%2Fgift%2Fproduct%2F20230516143408_834e86474204499b9fb85a2a3911ddfa.jpg', '2023-06-10 13:40:40.044709')"
        );

        jdbcTemplate.execute("" +
                "INSERT INTO ORDER_HISTORY(order_Id, address, message, product_Id, quantity, receiver, total_Price, user_Id, title, company, description, image_Url, created_At)" +
                " VALUES(11, '성동구', '감사합니다', 1, 1, '유정11', 2000, 'a111', '[단독각인] 캔디 글레이즈 컬러밤', '입생로랑', '투명하게 녹아 맑게 빛나는 컬러 글로우 밤', 'https://img1.kakaocdn.net/thumb/C320x320@2x.fwebp.q82/?fname=https%3A%2F%2Fst.kakaocdn.net%2Fproduct%2Fgift%2Fproduct%2F20230516143408_834e86474204499b9fb85a2a3911ddfa.jpg', '2023-06-10 13:40:40.044709')"
        );
        return "OK";
    }
}
