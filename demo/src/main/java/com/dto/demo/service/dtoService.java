package com.dto.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.dto.demo.lib.map;
import com.dto.demo.lib.testDto;
import com.dto.demo.lib.testDto3;

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

    public List<testDto> getList() {
        List<testDto> result = new ArrayList<testDto>();
        for (int i = 0; i < 100000; i++) {
            testDto t = new testDto();
            t.setId("test" + i);
            t.setName("test" + i);
            t.setEmail("test" + i + "@test.com");
            result.add(t);
        }
        return result;
    }

    public testDto3 getMixList() {
        testDto3 result = new testDto3();
        result.setId("test");
        result.setName("test");
        map m = new map();
        for (int i = 0; i < 100000; i++) {
            m.put("test" + i, "test" + i);
        }
        result.setParamMap(m);
        return result;
    }
}
