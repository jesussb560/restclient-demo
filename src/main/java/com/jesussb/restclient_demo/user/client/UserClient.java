package com.jesussb.restclient_demo.user.client;

import com.jesussb.restclient_demo.user.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import java.util.List;

@HttpExchange(url = "/users", accept = "application/json")
public interface UserClient {

    @GetExchange
    List<User> findAll();

    @GetExchange("/{id}")
    User findById(@PathVariable Integer id);

}
