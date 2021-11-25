package com.the_ring.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class KafkaConfig {

    private Properties properties;

    // 获取生产者配置
    public Properties getProducerProperties(Object object) {
        properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.94.61:9092,192.168.94.82:9092,192.168.94.63:9092");
        properties.put(ProducerConfig.RETRIES_CONFIG, 3);
        properties.put(ProducerConfig.ACKS_CONFIG, "all");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, getSerializer(object));
        return properties;
    }

    // 获取生产者配置
    public Properties getConsumerProperties(Object object, String groupId) {
        properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.94.61:9092,192.168.94.62:9092,192.168.94.63:9092");
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, getDeserializer(object));
        return properties;
    }

    // 获取响应类型的序列化类名称
    public static String getSerializer(Object object) {
        String name = object.getClass().getName();
        String[] split = name.split("\\.");
//        System.out.println("com.the_ring.kafka.deserializer." + split[split.length - 1] + "Serializer");
        return "com.the_ring.kafka.serializer." + split[split.length - 1] + "Serializer";
    }

    // 获取响应类型的反序列化类名称
    public static String getDeserializer(Object object) {
        String name = object.getClass().getName();
        String[] split = name.split("\\.");
//        System.out.println("com.the_ring.kafka.deserializer." + split[split.length - 1] + "Deserializer");
        return "com.the_ring.kafka.deserializer." + split[split.length - 1] + "Deserializer";
    }

}
