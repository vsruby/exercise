package com.vince.exercise.elasticsearch;

import java.util.List;

import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.vince.exercise.elasticsearch.MemberES;

public interface MemberESRepository extends ElasticsearchRepository<MemberES, Integer> {
    @Query("{\"multi_match\": {\"query\": \"?0\", \"fields\": [\"email\", \"name\"], \"type\": \"phrase_prefix\"}}")
    List<MemberES> findByTextSearch(String search);
}
