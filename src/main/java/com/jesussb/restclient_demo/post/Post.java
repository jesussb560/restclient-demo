package com.jesussb.restclient_demo.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class Post {

    private Integer id;
    private String title;
    private String body;
    private Integer userId;

}
