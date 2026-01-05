package com.jesussb.webclient_demo.post;

import com.jesussb.webclient_demo.post.client.PostClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PostRestController.class)
class PostRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PostClient postClient;

    @Test
    void findAll() throws Exception {

        String uri = "/api/v1/posts";
        when(postClient.findAll()).thenReturn(List.of(
                new Post(1, "post 1", "post 1 body", 1),
                new Post(2, "post 2", "post 2 body", 2)
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

        String uri = "/api/v1/posts/" + 1;
        when(postClient.findById(anyInt())).thenReturn(new Post(1, "post 1", "body 1", 1));

        mockMvc.perform(
                MockMvcRequestBuilders.get(uri)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                          "userId": 1,
                          "id": 1,
                          "title": "post 1",
                          "body": "body 1"
                        }
                        """));

    }

    @Test
    void create() throws Exception {

        String uri = "/api/v1/posts";
        String body = """
                        {
                          "userId": 1,
                          "id": 1,
                          "title": "post 1",
                          "body": "body 1"
                        }
                """;

        when(postClient.create(any())).thenReturn(new Post(1, "post 1", "body 1", 1));

        mockMvc.perform(
                MockMvcRequestBuilders.post(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body)
        )
                .andExpect(status().isCreated());
    }

    @Test
    void update() throws Exception {

        String uri = "/api/v1/posts/" + 1;
        String body = """
                        {
                          "userId": 1,
                          "id": 1,
                          "title": "post 1",
                          "body": "body 1"
                        }
                """;

        when(postClient.update(anyInt(), any())).thenReturn(new Post(1, "post 1", "body 1", 1));

        mockMvc.perform(
                        MockMvcRequestBuilders.put(uri)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(body)
                )
                .andExpect(status().isCreated());
    }

    @Test
    void patch() throws Exception {

        String uri = "/api/v1/posts/" + 1;
        String body = """
                        {
                          "title": "post 1",
                          "body": "body 1"
                        }
                """;

        when(postClient.patch(anyInt(), any())).thenReturn(new Post(1, "post 1", "body 1", 1));

        mockMvc.perform(
                        MockMvcRequestBuilders.patch(uri)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(body)
                )
                .andExpect(status().isCreated());
    }

    @Test
    void delete() throws Exception {

        String uri = "/api/v1/posts/" + 1;

        doNothing().when(postClient).delete(anyInt());

        mockMvc.perform(
                        MockMvcRequestBuilders.delete(uri)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated());
    }


}