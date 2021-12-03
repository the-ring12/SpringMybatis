package com.the_ring.kafka;

import com.the_ring.kafka.entity.Page;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Arrays;

public class Consumer {

    public static void main(String[] args) {
        KafkaConsumer<String, Page> consumer = new KafkaConsumer<String, Page>(new KafkaConfig().getConsumerProperties(new Page(), "test"));
        consumer.subscribe(Arrays.asList("test"));
        while (true) {
            ConsumerRecords<String, Page> records = consumer.poll(Duration.ofMillis(10000));
            for (ConsumerRecord<String, Page> record : records) {
                System.out.println(record.value());
            }
        }

    }
}
