package com.vince.exercise;

import java.util.Date;
import java.util.Optional;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;

import org.joda.time.DateTime;

import org.springframework.stereotype.Component;

@Component
public class QueryResolver implements GraphQLQueryResolver {

    private BookRepository books;
    private UserRepository users;

    public QueryResolver(BookRepository books, UserRepository users) {
        this.books = books;
        this.users = users;
    }

    public Iterable<Book> allBooks() {
        return this.books.findAll();
    }

    public Iterable<User> allUsers() {
        return this.users.findAll();
    }

    public Iterable<Book> allAvailableBooks() {
        return this.books.findByUserIdIsNullOrCheckedAtIsNull();
    }

    public Iterable<Book> allCheckedOutBooks() {
        return this.books.findByUserIdIsNotNullAndCheckedAtIsNotNull();
    }

    public Iterable<Book> allOverdueBooks() {
        DateTime dt = new DateTime(new Date());
        Date then = (dt.minusWeeks(3)).toDate();
        return this.books.findByUserIdIsNotNullAndCheckedAtBefore(then);
    }

    public Optional<Book> book(Integer id) {
        return this.books.findById(id);
    }

    public Optional<User> user(Integer id) {
        return this.users.findById(id);
    }
}
