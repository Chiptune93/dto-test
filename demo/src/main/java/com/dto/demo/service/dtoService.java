package com.dto.demo.service;

import com.dto.demo.lib.testDto;

import org.springframework.stereotype.Service;

@Service
public class dtoService {
    public testDto getData() {
        testDto dto = new testDto();
        dto.setId("testId");
        dto.setName("testName");
        dto.setEmail("test@test.com");
        return dto;
    }
}
