package com.jesussb.restclient_demo.post;

import com.jesussb.restclient_demo.post.client.PostClient;
import com.jesussb.restclient_demo.post.dto.PatchRequest;
import com.jesussb.restclient_demo.post.dto.PostRequest;
import com.jesussb.restclient_demo.post.dto.PutRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/posts")
public class PostRestController {

    private final PostClient postClient;

    @GetMapping
    public ResponseEntity<List<Post>> findAll(){
        return new ResponseEntity<>(postClient.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> findById(@PathVariable Integer id){
        return new ResponseEntity<>(postClient.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Post> create(@RequestBody PostRequest request){
        return new ResponseEntity<>(postClient.create(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> update(@PathVariable Integer id, @RequestBody PutRequest request){
        return new ResponseEntity<>(postClient.update(id, request), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Post> patch(@PathVariable Integer id, @RequestBody PatchRequest request){
        return new ResponseEntity<>(postClient.patch(id, request), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id){
        postClient.delete(id);
        return new ResponseEntity<>("deleted", HttpStatus.CREATED);
    }

}
