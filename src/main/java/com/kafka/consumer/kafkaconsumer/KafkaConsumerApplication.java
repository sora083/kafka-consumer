package com.kafka.consumer.kafkaconsumer;

import com.kafka.consumer.kafkaconsumer.consumer.SimpleConsoleConsumer;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;

@Slf4j
@SpringBootApplication
@EnableKafka
public class KafkaConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaConsumerApplication.class, args);
	}
//
//	@Override
//	public void run(String... args) throws Exception {
//		log.info("All received");
//	}
//
//	@KafkaListener(id = "twitter-consume", topics = "tweets")
//	public void listen(ConsumerRecord<?, ?> cr) throws Exception {
//		log.info(cr.toString());
//		//latch.countDown();
//	}
}
