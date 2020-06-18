package com.kenia.kafka.spring;

import com.alibaba.fastjson.JSONObject;
import com.kenia.kafka.entity.TestMapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;


@Component
public class TestMappingSender {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    public void sendMessage(TestMapping testMapping){
        kafkaTemplate.send("mappingtopic",JSONObject.toJSON(testMapping).toString());
    }
}