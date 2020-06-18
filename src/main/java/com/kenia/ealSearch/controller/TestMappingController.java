package com.kenia.ealSearch.controller;

import com.kenia.ealSearch.repository.TestMappingRepository;
import com.kenia.ealSearch.entity.TestMapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created by WKY
 */
@RestController
@RequestMapping("/testMapping")
public class TestMappingController {
    @Autowired
    private TestMappingRepository testMappingRepository;

    @RequestMapping("add")
    public void testSaveArticleIndex() {
    	TestMapping mapping = new TestMapping();
    	mapping.setId(2L);
    	mapping.setLayer("this is a test layer");
    	mapping.setMethod("this is a test kafka send method");
    	mapping.setProtocol("the protocol of kafka is tcp");
    	mapping.setStatus("0 means success, 1 means failure");
    	mapping.setTimestamp(new Date());
    	mapping.setTrace("this is a test trace");
    	mapping.setUrl("localhost:8080/send");

        testMappingRepository.save(mapping);
    }

}