package com.vince.exercise;

import java.util.Optional;

import com.coxautodev.graphql.tools.GraphQLResolver;

import org.springframework.stereotype.Component;

@Component
public class BookResolver implements GraphQLResolver<Book> {

    private MemberRepository members;

    public BookResolver(MemberRepository members) {
        this.members = members;
    }

    public Optional<Member> getMember(Book book) {
        return members.findById(book.getMember().getId());
    }
}
