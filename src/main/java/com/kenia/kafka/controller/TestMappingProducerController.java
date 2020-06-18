package com.kenia.kafka.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kenia.kafka.entity.TestMapping;
import com.kenia.kafka.spring.TestMappingSender;

@RestController
@RequestMapping("/testMapping")
public class TestMappingProducerController {
	@Autowired
    private TestMappingSender testMappingSender;
 
    @RequestMapping("send")
    public String send(){
    	TestMapping mapping = new TestMapping();
    	mapping.setId(3L);
    	mapping.setLayer("this is a test layer");
    	mapping.setMethod("this is a test kafka send method");
    	mapping.setProtocol("the protocol of kafka is tcp");
    	mapping.setStatus("0 means success, 1 means failure");
    	mapping.setTimestamp(new Date());
    	mapping.setTrace("this is a test trace");
    	mapping.setUrl("localhost:8080/send");
//    	String msg="Whatever your past has been, you have a spotless future";
    	testMappingSender.sendMessage(mapping);
        return "success";
    }
}
