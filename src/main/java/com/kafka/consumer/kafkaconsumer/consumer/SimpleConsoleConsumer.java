package com.kafka.consumer.kafkaconsumer.consumer;

import com.fasterxml.jackson.databind.deser.std.StringDeserializer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class SimpleConsoleConsumer {
    public static void main(String... args) {
        SimpleConsoleConsumer consoleConsumer = new SimpleConsoleConsumer();
        consoleConsumer.consuming();
    }

    public void consuming() {
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "twitter-consume");
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        //properties.put("jackson.json.deserializer.type", LinkedHashMap.class);

        try (KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties)) {
            consumer.subscribe(Arrays.asList("tweets"));

            Set<String> collectIds = new TreeSet<>();

            long startTime = System.nanoTime();

            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(1000L);

                records.forEach(record -> {
                    String value = record.value();

                    System.out.println("=== received record: ");
                    System.out.println(value);
                });

                long elapsedTime = System.nanoTime() - startTime;

                if (TimeUnit.MINUTES.convert(elapsedTime, TimeUnit.NANOSECONDS) >= 1L) {
                    break;
                }
            }

            System.out.println();
            System.out.println("===== collected time / id");
            collectIds.forEach(System.out::println);
        }
    }
}

