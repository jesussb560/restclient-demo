package com.jesussb.restclient_demo.user;

import com.jesussb.restclient_demo.user.client.UserClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserRestController.class)
class UserRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserClient userClient;

    @Test
    void findAll() throws Exception {

        String uri = "/api/v1/users";
        when(userClient.findAll()).thenReturn(List.of(
                new User(1, "user 1", "username 1", "user1@email.com"),
                new User(2, "user 2", "username 2", "user2@email.com")
        ));

        mockMvc.perform(
                        MockMvcRequestBuilders.get(uri)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(status().isOk());
    }

    @Test
    void findById() throws Exception {

        String uri = "/api/v1/users/" + 1;
        when(userClient.findById(anyInt())).thenReturn(new User(1, "user 1", "username 1", "user1@email.com"));

        mockMvc.perform(
                        MockMvcRequestBuilders.get(uri)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                          "id": 1,
                          "name": "user 1",
                          "username": "username 1",
                          "email": "user1@email.com"
                        }
                        """));

    }

}