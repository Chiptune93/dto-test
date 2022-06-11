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
        // 추가되었어요!
        m.put("info11", "info11");
        m.put("info12", "info12");
        m.put("info13", "info13");
        m.put("info14", "info14");
        m.put("info15", "info15");
        m.put("info16", "info16");
        m.put("info17", "info17");
        m.put("info18", "info18");
        m.put("info19", "info19");
        m.put("info20", "info20");
        return m;
    }
}
