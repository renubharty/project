package com.intcomcorp.intcomcorpApplication.dto;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class Request extends ZabbixRequest {
    private Map<String, Object> params = new HashMap<>();
    public void putParam(String key, Object value) {
        params.put(key, value);
    }
    public Object removeParam(String key) {
        return params.remove(key);
    }
    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}