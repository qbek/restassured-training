package org.example.testdata;

import java.util.HashMap;
import java.util.Map;

public class TestDataManager {

    private Map<String, Object> data = new HashMap<>();

    public void setTestData(String key, Object value) {
        data.put(key, value);
    }

    public Object getTestData(String key) {
        return data.get(key);
    }

}
