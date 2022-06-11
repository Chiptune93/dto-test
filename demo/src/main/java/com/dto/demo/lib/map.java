package com.dto.demo.lib;

import java.util.LinkedHashMap;

import com.dto.demo.example.JavaUtil;

public class map extends LinkedHashMap<String, Object> {
    public Object put(String key, Object value) {
        return super.put(key, value);
    }

    public String getStr(Object key, String def) {
        return JavaUtil.NVL(super.get(key), def);
    }

    public String getStr(Object key) {
        return JavaUtil.toString(JavaUtil.NVL(super.get(key), ""));
    }

    public int getInt(Object key) {
        return JavaUtil.toInt(JavaUtil.NVL(super.get(key), -1));
    }

    public int getInt(Object key, int def) {
        return JavaUtil.NVL(super.get(key), def);
    }

    public long getLong(Object key) {
        return JavaUtil.toLong(JavaUtil.NVL(super.get(key), "-1"));
    }

    public long getLong(Object key, long def) {
        return JavaUtil.toLong(JavaUtil.toString(JavaUtil.NVL(super.get(key), def)));
    }

}
