package com.jesussb.webclient_demo.post.client;

import com.jesussb.webclient_demo.post.Post;
import com.jesussb.webclient_demo.post.dto.PatchRequest;
import com.jesussb.webclient_demo.post.dto.PostRequest;
import com.jesussb.webclient_demo.post.dto.PutRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.*;

import java.util.List;

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
