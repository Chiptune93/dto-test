package com.dto.demo.lib;

import java.util.HashMap;

public class map extends HashMap<String, Object> {
    
    public String getStr(String key) {
        return this.get(key).toString();
    }
}
