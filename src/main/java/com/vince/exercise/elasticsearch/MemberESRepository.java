package com.vince.exercise.elasticsearch;

import java.util.List;

import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.vince.exercise.elasticsearch.MemberES;

public interface MemberESRepository extends ElasticsearchRepository<MemberES, Integer> {
    @Query("{\"match_phrase_prefix\": {\"email\":  \"?0\"}}")
    List<MemberES> findByEmail(String email);

    @Query("{\"match_phrase_prefix\": {\"name\":  \"?0\"}}")
    List<MemberES> findByName(String name);

    @Query("{\"bool\": {\"must\": [{\"match_phrase_prefix\": {\"email\":  \"?0\"}}, {\"match_phrase_prefix\": {\"name\":  \"?1\"}}]}}")
    List<MemberES> findByEmailAndName(String email, String name);

    @Query("{\"multi_match\": {\"query\": \"?0\", \"fields\": [\"email\", \"name\"], \"type\": \"phrase_prefix\"}}")
    List<MemberES> findByTextSearch(String search);
}
