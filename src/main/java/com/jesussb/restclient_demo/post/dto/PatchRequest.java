package com.jesussb.restclient_demo.post.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record PatchRequest(
        String title,
        String body
) {}
