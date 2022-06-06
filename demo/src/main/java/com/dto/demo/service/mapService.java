package com.dto.demo.service;

import com.dto.demo.lib.map;

import org.springframework.stereotype.Service;

@Service
public class mapService {

    public map getData() {
        map m = new map();
        m.put("id", "testId");
        m.put("name", "testName");
        m.put("email", "test@test.com");
        return m;
    }
}
