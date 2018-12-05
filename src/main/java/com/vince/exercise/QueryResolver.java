package com.vince.exercise;

import java.util.Date;
import java.util.Optional;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;

import org.joda.time.DateTime;

import org.springframework.stereotype.Component;

@Component
public class QueryResolver implements GraphQLQueryResolver {

    private BookRepository books;
    private MemberRepository members;

    public QueryResolver(BookRepository books, MemberRepository members) {
        this.books = books;
        this.members = members;
    }

    public Iterable<Book> allBooks() {
        return this.books.findAll();
    }

    public Iterable<Member> allMembers() {
        return this.members.findAll();
    }

    public Iterable<Book> allAvailableBooks() {
        return this.books.findAllAvailable();
    }

    public Iterable<Book> allCheckedOutBooks() {
        return this.books.findByMemberIdIsNotNullAndCheckedAtIsNotNull();
    }

    public Iterable<Book> allOverdueBooks() {
        DateTime dt = new DateTime(new Date());
        Date then = (dt.minusWeeks(3)).toDate();
        return this.books.findByMemberIdIsNotNullAndCheckedAtBefore(then);
    }

    public Iterable<Member> allOverdueMembers() {
        DateTime dt = new DateTime(new Date());
        Date then = (dt.minusWeeks(3)).toDate();
        return this.members.findAllWithOverdueBooks(then);
    }

    public Optional<Book> book(Integer id) {
        return this.books.findById(id);
    }

    public Optional<Member> member(Integer id) {
        return this.members.findById(id);
    }
}
