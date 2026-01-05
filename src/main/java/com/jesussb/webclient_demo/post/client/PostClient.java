package com.jesussb.webclient_demo.post.client;

import com.jesussb.webclient_demo.post.Post;
import com.jesussb.webclient_demo.post.dto.PatchRequest;
import com.jesussb.webclient_demo.post.dto.PostRequest;
import com.jesussb.webclient_demo.post.dto.PutRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.*;

import java.util.List;

@HttpExchange(url = "https://jsonplaceholder.typicode.com", accept = "application/json")
public interface PostClient {

    @GetExchange("/posts")
    List<Post> findAll();

    @GetExchange("/posts/{id}")
    Post findById(@PathVariable Integer id);

    @PostExchange("/posts")
    Post create(@RequestBody PostRequest postRequest);

    @PutExchange("/posts/{id}")
    Post update(@PathVariable Integer id, @RequestBody PutRequest putRequest);

    @PatchExchange("/posts/{id}")
    Post patch(@PathVariable Integer id, @RequestBody PatchRequest patchRequest);

    @DeleteExchange("/posts/{id}")
    void delete(@PathVariable Integer id);

}
