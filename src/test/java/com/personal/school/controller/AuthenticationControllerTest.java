package com.personal.school.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void auth_InvalidPassword() throws Exception {
        URI uri = new URI("/auth");
        String json = "{\"email\":\"guilherme_scherer@outlook.com\",\"password\":\"123316\"}";

        mockMvc.perform(
                MockMvcRequestBuilders.post(uri)
                .content(json)
                .contentType(APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers
                .status()
                .is(400));

    }

}
