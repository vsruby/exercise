package com.vince.exercise.elasticsearch;

import java.util.List;

import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.vince.exercise.elasticsearch.BookES;

public interface BookESRepository extends ElasticsearchRepository<BookES, Integer> {
    @Query("{\"match_phrase_prefix\": {\"summary\":  \"?0\"}}")
    List<BookES> findBySummary(String summary);

    @Query("{\"match_phrase_prefix\": {\"title\":  \"?0\"}}")
    List<BookES> findByTitle(String title);

    @Query("{\"bool\": {\"must\": [{\"match_phrase_prefix\": {\"summary\":  \"?0\"}}, {\"match_phrase_prefix\": {\"title\":  \"?1\"}}]}}")
    List<BookES> findBySummaryAndTitle(String summary, String title);

    @Query("{\"multi_match\": {\"query\": \"?0\", \"fields\": [\"title\", \"summary\"], \"type\": \"phrase_prefix\"}}")
    List<BookES> findByTextSearch(String search);
}
