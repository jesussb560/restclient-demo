package com.jesussb.webclient_demo.post.dto;

public record PostRequest(
        String title,
        String body,
        Integer userId
) {
}
