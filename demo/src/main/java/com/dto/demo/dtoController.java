package com.dto.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dto.demo.lib.map;
import com.dto.demo.lib.testDto;
import com.dto.demo.lib.testDto3;
import com.dto.demo.service.dtoService;
import com.dto.demo.service.mapService;

@RestController
public class dtoController {

    @Autowired
    dtoService ds;

    @Autowired
    mapService ms;

    @GetMapping(value = "/ds")
    public testDto dsTest() {
        StopWatch s = new StopWatch();
        s.start("dto class test");
        testDto result = ds.getData();
        s.stop();
        System.out.println(s.prettyPrint());
        return result;
    }

    @GetMapping(value = "/ms")
    public map msTest() {
        StopWatch s = new StopWatch();
        s.start("map test");
        map result = ms.getData();
        s.stop();
        System.out.println(s.prettyPrint());
        return result;
    }

    @GetMapping(value = "/dsList")
    public List<testDto> dsListTest() {
        StopWatch s = new StopWatch();
        s.start("dto class test");
        List<testDto> result = ds.getList();
        s.stop();
        System.out.println(s.prettyPrint());
        return result;
    }

    @GetMapping(value = "/msList")
    public List<map> msListTest() {
        StopWatch s = new StopWatch();
        s.start("map test");
        List<map> result = ms.getList();
        s.stop();
        System.out.println(s.prettyPrint());
        return result;
    }

    @GetMapping(value = "/mixList")
    public testDto3 mixListTest() {
        StopWatch s = new StopWatch();
        s.start("mix test");
        testDto3 result = ds.getMixList();
        s.stop();
        System.out.println(s.prettyPrint());
        return result;
    }

}
