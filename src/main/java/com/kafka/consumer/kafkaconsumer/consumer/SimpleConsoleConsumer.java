package com.kafka.consumer.kafkaconsumer.consumer;

import com.fasterxml.jackson.databind.deser.std.StringDeserializer;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Properties;

@Slf4j
@Component
@EnableKafka
public class SimpleConsoleConsumer {

    @KafkaListener(id = "twitter-consume", topics = "tweets")
    public void listen() {
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.0.14:9092");
        //properties.put(ConsumerConfig.GROUP_ID_CONFIG, "twitter-consume");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        //properties.put("jackson.json.deserializer.type", LinkedHashMap.class);

        try (KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties)) {
            consumer.subscribe(Arrays.asList("tweets"));
            ConsumerRecords<String, String> records = consumer.poll(1000L);
            records.forEach(r -> log.info(r.key() + ":" + r.value()));
        }
    }
}

