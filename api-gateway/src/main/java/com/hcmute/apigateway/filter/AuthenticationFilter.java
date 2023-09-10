package com.hcmute.apigateway.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.net.HttpHeaders;
import com.hcmute.apigateway.dto.ErrorResponse;
import com.hcmute.apigateway.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;


@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Autowired
    private RouteValidator validator;

//    @Autowired
//    private WebClient.Builder webClientBuilder;
//    @Autowired
//    private RestTemplate template;
    @Autowired
    private JwtUtil jwtUtil;


    

    public AuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            ServerHttpResponse response = exchange.getResponse();
            System.out.println("You are calling " + exchange.getRequest().getPath());
            if (validator.isSecured.test(exchange.getRequest())) {
                System.out.println("START AUTHENTICATION");
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
                    response.setStatusCode(HttpStatus.BAD_REQUEST);
                    ErrorResponse errorResponse = new ErrorResponse(false, 400, "Missing token", "");
                    try {
                        System.out.println("AUTHENTICATION IS FAILED");
                        return response.writeWith(Mono.just(response.bufferFactory().wrap(
                                new ObjectMapper().writeValueAsBytes(errorResponse)
                        )));
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                }


                try {
//                    String authHeader = exchange.getRequest().getHeaders().getFirst("Authorization");
                    String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                    if (authHeader != null && authHeader.startsWith("Bearer ")) {
                        authHeader = authHeader.substring(7);
                    }
//                    template.getForObject(
//                            "http://IDENTITY-SERVICE/api/auth/validate?token=" + authHeader,
//                            String.class
//                    );
                    jwtUtil.decode(authHeader);
                } catch (Exception e) {
                    System.out.println("AUTHENTICATION IS FAILED");




                    e.printStackTrace();
                    response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
                    response.setStatusCode(HttpStatus.UNAUTHORIZED);
                    ErrorResponse errorResponse = new ErrorResponse(false, 401, "Token is invalid", e.getMessage());
                    try {
                        return response.writeWith(Mono.just(response.bufferFactory().wrap(
                                new ObjectMapper().writeValueAsBytes(errorResponse)
                        )));
                    } catch (JsonProcessingException err) {
                        err.printStackTrace();
                    }
                }
            }
            return chain.filter(exchange);
        });
    }

    public static class Config {

    }
}