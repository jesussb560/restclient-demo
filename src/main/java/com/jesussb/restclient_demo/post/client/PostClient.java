package com.jesussb.restclient_demo.post.client;

import com.jesussb.restclient_demo.post.Post;
import com.jesussb.restclient_demo.post.dto.PatchRequest;
import com.jesussb.restclient_demo.post.dto.PostRequest;
import com.jesussb.restclient_demo.post.dto.PutRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.*;

import java.util.List;

/**
 * Rest Client for {@code /posts} resource using the declarative HTTP interfaces
 * Base URL configured in {@link com.jesussb.restclient_demo.common.HttpClientConfig}
 *
 * @version 1.0.0
 */
@HttpExchange(url = "posts", accept = "application/json")
public interface PostClient {

    @GetExchange
    List<Post> findAll();

    @GetExchange("/{id}")
    Post findById(@PathVariable Integer id);

    @PostExchange
    Post create(@RequestBody PostRequest postRequest);

    @PutExchange("/{id}")
    Post update(@PathVariable Integer id, @RequestBody PutRequest putRequest);

    @PatchExchange("/{id}")
    Post patch(@PathVariable Integer id, @RequestBody PatchRequest patchRequest);

    @DeleteExchange("/{id}")
    void delete(@PathVariable Integer id);

}
