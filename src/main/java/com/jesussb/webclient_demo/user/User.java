package com.jesussb.webclient_demo.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class User {
    private Integer id;
    private String name;
    private String username;
    private String email;
}
