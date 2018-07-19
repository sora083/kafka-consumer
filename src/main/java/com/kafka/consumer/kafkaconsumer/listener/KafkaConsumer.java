package com.kafka.consumer.kafkaconsumer.listener;

import com.kafka.consumer.kafkaconsumer.bean.Tweet;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class KafkaConsumer {

	private final KafkaConsumeHandler kafkaConsumeHandler;

	@KafkaListener(id = "tweets", topics = "tweets")
	public void listen(String tweet) {
		kafkaConsumeHandler.handleTwitterStream(tweet);
	}
}
