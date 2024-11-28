package com.example.trace.userservice.service;

import com.example.trace.userservice.client.AddressServiceClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class UserService {

    @Autowired
    private AddressServiceClient addressServiceClient;

    @Autowired
    RestTemplate restTemplate;

    public String getUserAddressWithFeign(String userId) {
        log.info("Inside UserService - Feign - address for user: {}", userId);
        String user = "User: " + userId;
        String address = addressServiceClient.getAddressForFeign(userId);
        return user + " | " + address;
    }

    public String getUserAddressWithRest(String userId) {
        String addressServiceUrl = "http://localhost:8081/addresses/rest" + userId;
        log.info("Inside UserService - Rest - address for user: {}", userId);

        ResponseEntity<String> response = restTemplate.getForEntity(addressServiceUrl, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else {
            throw new RuntimeException("Failed to retrieve address: " + response.getStatusCode());
        }
    }
}