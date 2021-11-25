package com.the_ring.kafka.deserializer;

import com.the_ring.kafka.entity.Page;
import com.the_ring.util.JsonSerializerUtil;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.UnsupportedEncodingException;
import java.util.Map;

public class PageDeserializer implements Deserializer<Page> {
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public Page deserialize(String s, byte[] bytes) {
        if (bytes == null) {
            System.out.println("bytes is null");
            return null;
        }

        Page page = null;

        try {
            String jsonString = new String(bytes, "UTF-8");
            page = JsonSerializerUtil.jsonToObject(jsonString, Page.class);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return page;
    }


    @Override
    public void close() {

    }
}
