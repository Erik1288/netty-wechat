package com.eric.serialize.impl;

import com.alibaba.fastjson.JSON;
import com.eric.serialize.Serializer;
import com.eric.serialize.SerializerType;

/**
 * User: Eric
 * Date: 15/10/2018
 */
public class JSONSerializer implements Serializer {
    @Override
    public byte getSerializerAlgorithm() {
        return SerializerType.JSON_TYPE;
    }

    @Override
    public byte[] serialize(Object object) {

        return JSON.toJSONBytes(object);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {

        return JSON.parseObject(bytes, clazz);
    }
}
