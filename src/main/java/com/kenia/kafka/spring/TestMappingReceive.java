package com.kenia.kafka.spring;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kenia.ealSearch.entity.TestMapping;
import com.kenia.ealSearch.repository.TestMappingRepository;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.elasticsearch.client.transport.TransportClient;
//import org.elasticsearch.client.transport.TransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class TestMappingReceive {
    @Autowired
    private TestMappingRepository testMappingRepository;

    private static final Logger logger = LoggerFactory.getLogger(TestMappingReceive.class);
    private static TransportClient client = null;

    @KafkaListener(topics = "mappingtopic", containerFactory = "batchFactory")
    public void listen(List<ConsumerRecord<?, ?>> records, Acknowledgment ack) {
        logger.info("records.size: " + records.size() + " in all");
        List<TestMapping> list = new ArrayList<TestMapping>();
        ObjectMapper mapper = new ObjectMapper();
        TestMapping testMapping =null;
        for (ConsumerRecord<?, ?> record : records) {
            try {
            	testMapping = mapper.readValue(record.value().toString(), TestMapping.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (null != testMapping) {
                list.add(testMapping);
            }
        }
        try {
            Iterable<TestMapping> ses = testMappingRepository.saveAll(list);
            System.out.println("es内保存==================="+list.size());
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            logger.info("start commit offset");
            ack.acknowledge();//手动提交偏移量
            logger.info("stop commit offset");
        }
    }
}