package com.jesussb.webclient_demo.common;

import com.jesussb.webclient_demo.post.client.PostClient;
import com.jesussb.webclient_demo.user.client.UserClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.service.registry.ImportHttpServices;

@Configuration(proxyBeanMethods = false)
@ImportHttpServices({PostClient.class, UserClient.class})
public class HttpClientConfig {
}
