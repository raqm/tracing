package com.example.trace.addressservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AddressService {
    public String getAddressForFeign(String userId) {
        log.info("Inside AddressService - FEIGN - address for user: {}", userId);
        return "Address for user: " + userId;
    }

    public String getAddressForRest(String userId) {
        log.info("Inside AddressService - REST - address for user: {}", userId);
        return "Address for user " + userId + ": 123 Main St, Springfield, USA";
    }

    public String getAddressForWebClient(String userId) {
        log.info("Inside AddressService - WebClient - address for user: {}", userId);
        return "Address for user " + userId + ": 123 Main St, Springfield, USA";
    }
}
