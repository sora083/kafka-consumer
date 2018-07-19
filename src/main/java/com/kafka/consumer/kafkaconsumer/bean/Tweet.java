package com.kafka.consumer.kafkaconsumer.bean;

import java.time.LocalDateTime;
import java.util.Date;
import lombok.Data;

@Data
public class Tweet {
	private Long id;
	private String user;
	private String screenName;
	private String tweet;
	private Date createdAt;
}
