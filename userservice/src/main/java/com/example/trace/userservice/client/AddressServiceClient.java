package com.example.trace.userservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "address-service", url = "http://localhost:8081")
public interface AddressServiceClient {

    @GetMapping("/addresses/{userId}")
    String getAddressForFeign(@PathVariable String userId);
}