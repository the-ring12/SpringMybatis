package com.the_ring.kafka.serializer;

import com.the_ring.kafka.entity.Page;
import com.the_ring.util.JsonSerializerUtil;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class PageSerializer implements Serializer<Page> {
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public byte[] serialize(String s, Page page) {
        return JsonSerializerUtil.objectToString(page).getBytes();
    }


    @Override
    public void close() {

    }
}
