package com.kafka.consumer.kafkaconsumer.listener;

import com.kafka.consumer.kafkaconsumer.bean.Tweet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaConsumeHandler {

	public void handleTwitterStream(Tweet tweet) {
		log.info("consuming...");
		log.info("tweet: {}", tweet.toString());
	}
}
