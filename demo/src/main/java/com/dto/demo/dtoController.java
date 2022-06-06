package com.dto.demo;

import com.dto.demo.lib.map;
import com.dto.demo.lib.testDto;
import com.dto.demo.service.dtoService;
import com.dto.demo.service.mapService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class dtoController {

    @Autowired
    dtoService ds;

    @Autowired
    mapService ms;

    @GetMapping(value = "/ds")
    public testDto dsTest() {
        return ds.getData();
    }

    @GetMapping(value = "/ms")
    public map msTest() {
        return ms.getData();
    }

}
