package com.jesussb.webclient_demo.post.dto;

public record PutRequest(
        Integer id,
        String title,
        String body,
        Integer userId
) {
}
