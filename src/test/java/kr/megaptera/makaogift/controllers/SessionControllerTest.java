package kr.megaptera.makaogift.controllers;

import kr.megaptera.makaogift.exceptions.LoginFailed;
import kr.megaptera.makaogift.models.Account;
import kr.megaptera.makaogift.services.LoginService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SessionController.class)
@ActiveProfiles("test")
class SessionControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LoginService loginService;

    @BeforeEach
    void setUp() {
        Account account = Account.fake("a111");

        given(loginService.login("a111", "Aa1!!!!!")).willReturn(account);
        given(loginService.login("a111", "xxx")).willThrow(new LoginFailed());
    }

    @Test
    void loginSuccess() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/session")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{" +
                                        "\"userId\":\"a111\"," +
                                        "\"password\":\"Aa1!!!!!\"" +
                                        "}")
                )
                .andExpect(status().isCreated())
                .andExpect(content().string(
                        containsString("\"amount\":")
                ));
    }
}
