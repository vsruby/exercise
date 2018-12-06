package com.vince.exercise.elasticsearch;

import java.util.List;

import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.vince.exercise.elasticsearch.BookES;

public interface BookESRepository extends ElasticsearchRepository<BookES, Integer> {
    @Query("{\"multi_match\": {\"query\": \"?0\", \"fields\": [\"title\", \"summary\"], \"type\": \"phrase_prefix\"}}")
    List<BookES> findByTextSearch(String search);
}
