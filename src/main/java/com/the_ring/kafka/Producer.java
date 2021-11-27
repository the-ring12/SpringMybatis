package com.the_ring.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class Producer implements Runnable {

    private Object object;

    public Producer(Object object) {
        this.object = object;
    }

    @Override
    public void run() {
        KafkaConfig config = new KafkaConfig();
        Properties properties = config.getProducerProperties(object);
        KafkaProducer<String, Object> producer = new KafkaProducer<>(properties);
        ProducerRecord<String, Object> record = new ProducerRecord<>("page", "key", object);
        try {
            RecordMetadata recordMetadata = producer.send(record).get();
            System.out.println("partition:" + recordMetadata.partition() + " offset:" + recordMetadata.offset());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
