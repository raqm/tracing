package com.example.trace.userservice.controller;

import com.example.trace.userservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    WebClient webClient;


    @GetMapping("/feign/{userId}")
    public String getUserAddressWithFeign(@PathVariable String userId) {
        log.info("Inside UserController - FEIGN - address for user: {}", userId);
        return userService.getUserAddressWithFeign(userId);
    }

    @GetMapping("/rest/{userId}")
    public String getUserAddressWithRest(@PathVariable String userId) {
        log.info("Inside UserController - REST- address for user: {}", userId);
        return userService.getUserAddressWithRest(userId);
    }

    @GetMapping("/webclient/{userId}")
    public Mono<String> getUserAddressWithWebClient(@PathVariable String userId) {
        log.info("Inside UserController - WebClient - address for user: {}", userId);
        return webClient.get()
                .uri("/addresses/webclient/{userId}", userId)
                .retrieve()
                .bodyToMono(String.class)
                .onErrorResume(e -> {
                    // Handle error (e.g., log it, return a default value, etc.)
                    return Mono.just("Error retrieving address: " + e.getMessage());
                });
    }
}