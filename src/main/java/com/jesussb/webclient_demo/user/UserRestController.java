package com.jesussb.webclient_demo.user;

import com.jesussb.webclient_demo.user.client.UserClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/users")
public class UserRestController {

    private final UserClient userClient;

    @GetMapping
    public ResponseEntity<List<User>> findAll(){
        return new ResponseEntity<>(userClient.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Integer id){
        return new ResponseEntity<>(userClient.findById(id), HttpStatus.OK);
    }

}
