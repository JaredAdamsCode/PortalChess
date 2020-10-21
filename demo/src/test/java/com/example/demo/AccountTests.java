package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;


import static org.assertj.core.api.Assertions.assertThat;

@ContextConfiguration(locations =  {"classpath*:/app-context.xml"})
@WebMvcTest(DemoApplication.class)
public class AccountTests {


    @Test
    public void createAccountObject() throws Exception {

        Account testAccount = new Account();
        testAccount.setId(1);
        testAccount.setUsername("Steve");
        testAccount.setEmail("steve@email.com");
        testAccount.setGames_Played(2);
        testAccount.setGames_Won(0);

        assertThat(testAccount.getId()).isEqualTo(1);
        assertThat(testAccount.getUsername()).isEqualTo("Steve");
        assertThat(testAccount.getEmail()).isEqualTo("steve@email.com");
        assertThat(testAccount.getGames_Played()).isEqualTo(2);
        assertThat(testAccount.getGames_Won()).isEqualTo(0);

    }



}
