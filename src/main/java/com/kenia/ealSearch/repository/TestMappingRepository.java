package com.kenia.ealSearch.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.kenia.ealSearch.entity.TestMapping;

public interface TestMappingRepository extends ElasticsearchRepository<TestMapping, Long> {
}