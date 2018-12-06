package com.vince.exercise;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;

import org.joda.time.DateTime;

import org.springframework.stereotype.Component;

import com.vince.exercise.elasticsearch.BookES;
import com.vince.exercise.elasticsearch.BookESRepository;
import com.vince.exercise.elasticsearch.MemberES;
import com.vince.exercise.elasticsearch.MemberESRepository;

@Component
public class QueryResolver implements GraphQLQueryResolver {

    private BookRepository books;
    private BookESRepository booksES;
    private MemberRepository members;
    private MemberESRepository membersES;

    public QueryResolver(BookRepository books, BookESRepository booksES, MemberRepository members, MemberESRepository membersES) {
        this.books = books;
        this.booksES = booksES;
        this.members = members;
        this.membersES = membersES;
    }

    public Iterable<Book> allBooks(String title, String summary) {
        ArrayList<Integer> ids = new ArrayList<Integer>();

        if (title != null && summary != null) {
            this.booksES.findBySummaryAndTitle(summary, title)
                .forEach(match -> ids.add(match.getId()));
        } else if (title != null) {
            this.booksES.findByTitle(title)
                .forEach(match -> ids.add(match.getId()));
        } else if (summary != null) {
            this.booksES.findBySummary(summary)
                .forEach(match -> ids.add(match.getId()));
        }

        if (title != null || summary != null) {
            return this.books.findAllById(ids);
        }

        return this.books.findAll();
    }

    public Iterable<Member> allMembers(String email, String name) {
        ArrayList<Integer> ids = new ArrayList<Integer>();

        if (email != null && name != null) {
            this.membersES.findByEmailAndName(email, name)
                .forEach(match -> ids.add(match.getId()));
        } else if (email != null) {
            this.membersES.findByEmail(email)
                .forEach(match -> ids.add(match.getId()));
        } else if (name != null) {
            this.membersES.findByName(name)
                .forEach(match -> ids.add(match.getId()));
        }

        if (email != null || name != null) {
            return this.members.findAllById(ids);
        }

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

    public Iterable<Book> searchBooks(String search) {
        ArrayList<Integer> ids = new ArrayList<Integer>();

        this.booksES.findByTextSearch(search).forEach(match -> ids.add(match.getId()));

        return this.books.findAllById(ids);
    }

    public Iterable<Member> searchMembers(String search) {
        ArrayList<Integer> ids = new ArrayList<Integer>();

        this.membersES.findByTextSearch(search).forEach(match -> ids.add(match.getId()));

        return this.members.findAllById(ids);
    }

    public Optional<Book> book(Integer id) {
        return this.books.findById(id);
    }

    public Optional<Member> member(Integer id) {
        return this.members.findById(id);
    }
}
