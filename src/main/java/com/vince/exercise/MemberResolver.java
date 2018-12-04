package com.vince.exercise;

import java.util.List;

import com.coxautodev.graphql.tools.GraphQLResolver;

import org.springframework.stereotype.Component;

@Component
public class MemberResolver implements GraphQLResolver<Member> {

    private BookRepository books;

    public MemberResolver(BookRepository books) {
        this.books = books;
    }

    public List<Book> getBooks(Member member) {
        return books.findByMemberId(member.getId());
    }
}
