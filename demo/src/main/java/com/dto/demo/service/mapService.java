package com.dto.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.dto.demo.lib.map;

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

    public List<map> getList() {
        List<map> result = new ArrayList<map>();
        for (int i = 0; i < 100000; i++) {
            map m = new map();
            m.put("id", "test" + i);
            m.put("name", "test" + i);
            m.put("email", "test" + i + "@test.com");
            result.add(m);
        }
        return result;
    }
}
