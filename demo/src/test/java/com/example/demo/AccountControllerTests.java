package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
public class AccountControllerTests {



    @Autowired
    private AccountController controller;
    private MockMvc mockMvc;

    @Test
    void contextLoads() throws Exception{
        assertThat(controller).isNotNull();
    }

    @Test
    void validInput_thenReturns200() throws Exception {
       // mockMvc.perform(post("/account").contentType("application/json")).andExpect(status.isOk());
    }


}
