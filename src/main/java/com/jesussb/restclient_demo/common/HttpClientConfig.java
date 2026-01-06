package com.jesussb.restclient_demo.common;

import com.jesussb.restclient_demo.post.client.PostClient;
import com.jesussb.restclient_demo.user.client.UserClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.support.RestClientHttpServiceGroupConfigurer;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.service.registry.ImportHttpServices;

import java.util.Optional;
import java.util.UUID;

/**
 * Configuration class for the Rest client HTTP interfaces
 * <p>
 *     This class is using the new {@link ImportHttpServices} annotation for services and groups configurations.
 *     All HTTP interfaces using @HttpExchange must be included here, otherwise, Spring will not be able to register a bean for each
 *     client.
 * </p>
 *
 * @version 1.0.0
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@ImportHttpServices(group = "jsonplaceholder", types = {PostClient.class, UserClient.class})
public class HttpClientConfig {

    public static final String HEADER = "x-custom-header-id";

    /**
     * Group configurer method for the jsonplaceholder clients,
     * including the base url, custom HTTP status handling and a request interceptor for headers.
     * @return a configuration for each client.
     */
    @Bean
    RestClientHttpServiceGroupConfigurer groupConfigurer(){
        return groups -> {
            groups.filterByName("jsonplaceholder")
                    .forEachClient((group, b) -> b
                            .baseUrl("https://jsonplaceholder.typicode.com/")
                            .defaultStatusHandler(httpStatusCode -> httpStatusCode == HttpStatus.UNAUTHORIZED, (req, res) -> {throw new RuntimeException("Custom 401 error");})
                            .defaultStatusHandler(HttpStatusCode::is4xxClientError, (req, res) -> {throw new RuntimeException("Custom 400 error");})
                            .defaultStatusHandler(HttpStatusCode::is5xxServerError, (req, res) -> {throw new RuntimeException("Custom 500 error");})
                            .requestInterceptor((req, body, exec) -> {

                                String customHeaderId = Optional.ofNullable(RequestContextHolder.getRequestAttributes())
                                        .filter(ServletRequestAttributes.class::isInstance)
                                        .map(attrs -> ((ServletRequestAttributes) attrs).getRequest().getHeader(HEADER))
                                        .filter(id -> !id.isBlank())
                                        .orElseGet(() -> "generated-" + UUID.randomUUID());

                                log.info("custom header id : {}", customHeaderId);

                                req.getHeaders().add(HEADER, customHeaderId);
                                return exec.execute(req, body);

                            })
                    );
        };
    }



//simple
//    RestClientHttpServiceGroupConfigurer groupConfigurer() {
//        return groups -> {
//            groups.forEachClient((group,builder) -> builder
//                    .baseUrl("https://jsonplaceholder.typicode.com/")
//                    .build());
//        };
//    }

}
