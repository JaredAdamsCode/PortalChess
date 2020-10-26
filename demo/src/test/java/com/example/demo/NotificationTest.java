package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

@ContextConfiguration(locations =  {"classpath*:/app-context.xml"})
@WebMvcTest(DemoApplication.class)
public class NotificationTest {

    @Test
    public void createAccountObject(){

        Notification testNotification = new Notification();

        testNotification.setId(5);
        testNotification.setSender(33);
        testNotification.setReceiver(21);
        testNotification.setMessage("Test Message");
        testNotification.setMatch(4);

        assertThat(testNotification.getId()).isEqualTo(5);
        assertThat(testNotification.getSender()).isEqualTo(33);
        assertThat(testNotification.getReceiver()).isEqualTo(21);
        assertThat(testNotification.getMessage()).isEqualTo("Test Message");
        assertThat(testNotification.getMatch()).isEqualTo(4);
    }

}
