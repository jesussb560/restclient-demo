package com.jesussb.restclient_demo.post.dto;

public record PutRequest(
        Integer id,
        String title,
        String body,
        Integer userId
) {
}
