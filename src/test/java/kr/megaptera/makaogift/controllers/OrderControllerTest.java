package kr.megaptera.makaogift.controllers;

import kr.megaptera.makaogift.models.Order;
import kr.megaptera.makaogift.models.UserId;
import kr.megaptera.makaogift.services.OrderService;
import kr.megaptera.makaogift.services.PresentService;
import kr.megaptera.makaogift.utils.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
class OrderControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @MockBean
    private PresentService presentService;

    @SpyBean
    private JwtUtil jwtUtil;

    @Test
    void list() throws Exception {
        String token = jwtUtil.encode(new UserId("a111"));

        UserId userId = new UserId("a111");

        Order order = mock(Order.class);

        given(orderService.list(userId))
                .willReturn(List.of(order));

        mockMvc.perform(MockMvcRequestBuilders.get("/orders")
                        .header("Authorization", "Bearer " + token)
                )
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"orders\":[")));

        verify(orderService).list(userId);
    }

    @Test
    void present() throws Exception {
        String token = jwtUtil.encode(new UserId("a111"));

        LocalDateTime createdAt = LocalDateTime.now();

        given(presentService.present(new UserId("a111"), 1L, "[단독각인] 캔디 글레이즈 컬러밤", "입생로랑", "투명하게 녹아 맑게 빛나는 컬러 글로우 밤", "https://img1.kakaocdn.net/thumb/C320x320@2x.fwebp.q82/?fname=https%3A%2F%2Fst.kakaocdn.net%2Fproduct%2Fgift%2Fproduct%2F20230516143408_834e86474204499b9fb85a2a3911ddfa.jpg", 2, "이름", "주소", "메시지", createdAt))
                .willReturn(Order.fake());

        String createdAtString = createdAt.toString();

        mockMvc.perform(MockMvcRequestBuilders.post("/orders")
                        .header("Authorization", "Bearer " + token)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"userId\":\"a111\"," +
                                "\"productId\":\"1\"," +
                                "\"title\":\"[단독각인] 캔디 글레이즈 컬러밤\"," +
                                "\"company\":\"입생로랑\"," +
                                "\"description\":\"투명하게 녹아 맑게 빛나는 컬러 글로우 밤\"," +
                                "\"imageUrl\":\"https://img1.kakaocdn.net/thumb/C320x320@2x.fwebp.q82/?fname=https%3A%2F%2Fst.kakaocdn.net%2Fproduct%2Fgift%2Fproduct%2F20230516143408_834e86474204499b9fb85a2a3911ddfa.jpg\"," +
                                "\"quantity\":\"2\"," +
                                "\"receiver\":\"이름\"," +
                                "\"address\":\"주소\"," +
                                "\"message\":\"메시지\"," +
                                "\"createdAt\":\"" + createdAtString + "\"" +
                                "}"))
                .andExpect(status().isCreated());
    }
}
