package com.vince.exercise.elasticsearch;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.vince.exercise.elasticsearch.MemberES;

public interface MemberESRepository extends ElasticsearchRepository<MemberES, Integer> {
    //
}
