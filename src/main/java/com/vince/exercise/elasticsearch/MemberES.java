package com.vince.exercise.elasticsearch;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import com.vince.exercise.Member;

@Document(indexName = "members", type = "member")
public class MemberES {

    @Id
    private Integer id;

    private String email;

    private String name;

    public MemberES() {
        //
    }

    public MemberES(Member member) {
        this.id = member.getId();
        this.email = member.getEmail();
        this.name = member.getName();
    }

    public MemberES(Integer id, String email, String name) {
        this.id = id;
        this.email = email;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
