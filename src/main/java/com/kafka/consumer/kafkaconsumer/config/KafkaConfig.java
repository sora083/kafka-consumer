package com.kafka.consumer.kafkaconsumer.config;

import com.kafka.consumer.kafkaconsumer.bean.Tweet;
import java.util.Map;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@EnableKafka
@Configuration
@ConfigurationProperties(prefix = "kafka.bootstrap-servers")
public class KafkaConfig {

	@Autowired
	private KafkaProperties kafkaProperties;

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
		Map<String, Object> consumerProperties = kafkaProperties.buildConsumerProperties();
		ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
		ConsumerFactory<String, String> consumerFactory = new DefaultKafkaConsumerFactory<>(consumerProperties, new StringDeserializer(), new StringDeserializer());
		factory.setConsumerFactory(consumerFactory);
		return factory;
	}
}
