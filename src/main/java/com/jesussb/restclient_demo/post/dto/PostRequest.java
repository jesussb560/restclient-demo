package com.jesussb.restclient_demo.post.dto;

public record PostRequest(
        String title,
        String body,
        Integer userId
) {
}
