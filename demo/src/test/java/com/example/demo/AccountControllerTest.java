package com.example.demo;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ContextConfiguration(locations =  {"classpath*:/app-context.xml"})
@WebMvcTest(DemoApplication.class)
public class AccountControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AccountController testAccountController;

    @Test
    public void createAccount() throws Exception
    {

        Account testAccount = new Account();
        testAccount.setId(1);
        testAccount.setUsername("Steve");
        testAccount.setEmail("steve@email.com");
        testAccount.setPassword("123456");
        testAccount.setGames_Played(2);
        testAccount.setGames_Won(0);

        mvc.perform(MockMvcRequestBuilders.post("/api/account")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(testAccount))
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());

    }

    @Test
    public void validateAccountTestInvalidEmail() {
        Account testAccount = new Account();
        testAccount.setId(1);
        testAccount.setUsername("Steve");
        testAccount.setEmail("steve");
        testAccount.setPassword("123456");
        testAccount.setGames_Played(2);
        testAccount.setGames_Won(0);

        String accountCheck = ValidateAccount.validate(testAccount);
        assert(accountCheck.equals("invalid email"));
    }

    @Test
    public void validateAccountTestInvalidPass() {
        Account testAccount = new Account();
        testAccount.setId(1);
        testAccount.setUsername("Steve");
        testAccount.setEmail("steve@gmail.com");
        testAccount.setPassword("");
        testAccount.setGames_Played(2);
        testAccount.setGames_Won(0);

        String accountCheck = ValidateAccount.validate(testAccount);
        assert(accountCheck.equals("invalid password"));
    }

    @Test
    public void validateAccountTestInvalidUsername() {
        Account testAccount = new Account();
        testAccount.setId(1);
        testAccount.setUsername("");
        testAccount.setEmail("steve@gmail.com");
        testAccount.setPassword("123456");
        testAccount.setGames_Played(2);
        testAccount.setGames_Won(0);

        String accountCheck = ValidateAccount.validate(testAccount);
        assert(accountCheck.equals("invalid username"));
    }

    @Test
    public void validateAccountTestValid(){
        Account testAccount = new Account();
        testAccount.setId(1);
        testAccount.setUsername("Steve");
        testAccount.setEmail("steve@email.com");
        testAccount.setPassword("123456");
        testAccount.setGames_Played(2);
        testAccount.setGames_Won(0);

        String accountCheck = ValidateAccount.validate(testAccount);
        assert(accountCheck.equals("valid"));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
