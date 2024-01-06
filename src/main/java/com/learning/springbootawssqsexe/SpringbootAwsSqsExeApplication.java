package com.learning.springbootawssqsexe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.aws.autoconfigure.context.ContextStackAutoConfiguration;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(exclude ={ContextStackAutoConfiguration.class})
@RestController
@CrossOrigin
public class SpringbootAwsSqsExeApplication {

	@Autowired
	private QueueMessagingTemplate queueMessagingTemplate;

	@Value("${cloud.aws.end-point.uri}")
	private String endpoint;

	@GetMapping("/send/{message}")
	public void sendMessageToQueue(@PathVariable String message){
		queueMessagingTemplate.send(endpoint, MessageBuilder.withPayload(message).build());
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringbootAwsSqsExeApplication.class, args);
	}

}
