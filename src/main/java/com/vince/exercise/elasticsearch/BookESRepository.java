package com.vince.exercise.elasticsearch;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.vince.exercise.elasticsearch.BookES;

public interface BookESRepository extends ElasticsearchRepository<BookES, Integer> {
    //
}
