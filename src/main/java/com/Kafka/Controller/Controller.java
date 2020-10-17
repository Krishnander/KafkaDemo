package com.Kafka.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Kafka.domain.POJOClass;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(value = "/kafka/api")
@EnableKafka
public class Controller {
	
	@Autowired
	KafkaTemplate<String, POJOClass> kafkaTemplate;
	
	ObjectMapper mapper;

	@PostMapping
	public void post(@RequestBody POJOClass pojo) {
		kafkaTemplate.send("DemoKafkaTopic", pojo);
	}
	
    @KafkaListener(topics = "DemoKafkaTopic")
    public void getFromKafka(String pojo){

        System.out.println(pojo);
        
    }

}
