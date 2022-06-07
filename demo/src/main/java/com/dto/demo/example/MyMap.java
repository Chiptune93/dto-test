package com.dto.demo.example;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@SuppressWarnings({ "serial", "unchecked" })
public class MyMap extends LinkedHashMap<Object, Object> {
    public Object put(Object key, Object value) {
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

    public List<MyCamelMap> getListMyCamelMap(Object key) {
        try {
            return (List<MyCamelMap>) super.get(key);
        } catch (Exception e) {
            return new ArrayList<MyCamelMap>();
        }
    }

    public List<MyMap> getListMyMap(Object key) {
        try {
            return (List<MyMap>) super.get(key);
        } catch (Exception e) {
            return new ArrayList<MyMap>();
        }
    }

    public MyMap getMyMap(Object key) {
        try {
            return (MyMap) super.get(key);
        } catch (Exception e) {
            return new MyMap();
        }
    }

    public MyCamelMap getMyCamelMap(Object key) {
        try {
            return (MyCamelMap) super.get(key);
        } catch (Exception e) {
            return new MyCamelMap();
        }
    }

    public JSONObject getJSONObject(Object key) {
        try {
            return JSONObject.fromObject(JavaUtil.unescapeHTML(JavaUtil.toString(super.get(key))));
        } catch (Exception e) {
            return new JSONObject();
        }
    }

    public JSONArray getJSONArray(Object key) {
        try {
            return JSONArray.fromObject(JavaUtil.unescapeHTML(JavaUtil.toString(super.get(key))));
        } catch (Exception e) {
            return new JSONArray();
        }
    }

    public String[] getStrArray(Object key) {
        try {
            return JavaUtil.toStrArray(super.get(key));
        } catch (Exception e) {
            return new String[0];
        }
    }

    public String[][] getStrArray2(Object key) {
        return JavaUtil.toStrArray2(super.get(key));
    }

    public String[][][] getStrArray3(Object key) {
        return JavaUtil.toStrArray3(super.get(key));
    }

    public int[] getIntArray(Object key) {
        try {
            return JavaUtil.toIntArray(super.get(key));
        } catch (Exception e) {
            return new int[0];
        }
    }
}