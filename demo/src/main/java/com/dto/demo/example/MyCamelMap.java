package com.dto.demo.example;

@SuppressWarnings({ "serial" })
public class MyCamelMap extends MyMap {
    public Object put(Object key, Object value) {
        return super.put(JavaUtil.camelCaseConverter((String) key), value);
    }
}