package com.example.trace.addressservice.controller;

import com.example.trace.addressservice.service.AddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/addresses")
@Slf4j
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping("/{userId}")
    public String getAddressForFeign(@PathVariable String userId) {
        log.info("Feign - Fetching address for user: {}", userId);
        return addressService.getAddressForFeign(userId);
    }

    @GetMapping("/rest/{userId}")
    public String getAddressForRest(@PathVariable String userId) {
        log.info("REST - Fetching address for user - {}", userId);
        return addressService.getAddressForRest(userId);
    }

    @GetMapping("/webclient/{userId}")
    public String getAddressForWebClient(@PathVariable String userId) {
        log.info("WebClient - Fetching address for user - {}", userId);
        return addressService.getAddressForWebClient(userId);
    }
}