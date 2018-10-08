package com.myaseen.storage.redis.storage.data;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class TestObject implements Serializable {

    Map<Object,Object> fields = new HashMap<>();

    public TestObject(){

    }

    public void addField(Object fieldName, Collection<? extends Object> fieldValues){
        fields.put(fieldName,fieldValues);
    }

    public Map<Object, Object> getFields() {
        return fields;
    }
}
