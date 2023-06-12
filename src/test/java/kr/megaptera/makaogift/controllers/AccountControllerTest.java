package kr.megaptera.makaogift.controllers;

import kr.megaptera.makaogift.exceptions.AccountNotFound;
import kr.megaptera.makaogift.models.Account;
import kr.megaptera.makaogift.models.UserId;
import kr.megaptera.makaogift.repositories.AccountRepository;
import kr.megaptera.makaogift.services.AccountService;
import kr.megaptera.makaogift.utils.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AccountController.class)
@ActiveProfiles("test")
class AccountControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    @MockBean
    private AccountRepository accountRepository;

    @SpyBean
    private JwtUtil jwtUtil;

    @Test
    void account() throws Exception {
        given(accountService.detail(any())).willReturn(Account.fake("a111"));

        String token = jwtUtil.encode(new UserId("a111"));

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/accounts/me")
                                .header("Authorization", "Bearer " + token)
                )
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"userId\":\"a111\"")
                ));
    }

    @Test
    void accountNotFound() throws Exception {
        String token = jwtUtil.encode(new UserId("a111"));

        given(accountService.detail(any()))
                .willThrow(new AccountNotFound(new UserId("a111")));

        mockMvc.perform(MockMvcRequestBuilders.get("/accounts/me")
                        .header("Authorization", "Bearer " + token)
                )
                .andExpect(status().isNotFound());
    }

    @Test
    void accountWithoutAccessToken() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/accounts/me"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void accountWithIncorrectAccessToken() throws Exception {
        String token = "xxx";

        mockMvc.perform(MockMvcRequestBuilders.get("/accounts/me")
                        .header("Authorization", "Bearer " + token)
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    void register() throws Exception {
        given(accountService.create(any())).willReturn(Account.fake("a111"));

        mockMvc.perform(MockMvcRequestBuilders.post("/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"id\":\"1\"," +
                                "\"name\":\"내이름\"," +
                                "\"userId\":\"a111\"" +
                                "}"))
                .andExpect(status().isCreated())
                .andExpect(content().string(
                        containsString("내이름")
                ));
    }
}
