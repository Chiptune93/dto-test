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
        dto.setInfo11("info11");
        dto.setInfo12("info12");
        dto.setInfo13("info13");
        dto.setInfo14("info14");
        dto.setInfo15("info15");
        dto.setInfo16("info16");
        dto.setInfo17("info17");
        dto.setInfo18("info18");
        dto.setInfo19("info19");
        dto.setInfo20("info20");
        return dto;
    }
}
