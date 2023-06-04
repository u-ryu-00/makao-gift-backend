package kr.megaptera.makaogift.controllers;

import kr.megaptera.makaogift.exceptions.AccountNotFound;
import kr.megaptera.makaogift.models.Account;
import kr.megaptera.makaogift.services.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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

    @Test
    void account() throws Exception {
        given(accountService.detail(any())).willReturn(Account.fake("a111"));

        mockMvc.perform(MockMvcRequestBuilders.get("/accounts/me"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"userId\":\"a111\"")
                ));
    }

    @Test
    void accountNotFound() throws Exception {
        given(accountService.detail(any()))
                .willThrow(new AccountNotFound("a111"));

        mockMvc.perform(MockMvcRequestBuilders.get("/accounts/me"))
                .andExpect(status().isNotFound());
    }
}
