package com.vince.exercise;

import java.util.Optional;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;

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

    public Iterable<Book> allCheckedOutBooks() {
        return this.books.findByUserIdIsNotNullAndCheckedAtIsNotNull();
    }

    public Optional<Book> book(Integer id) {
        return this.books.findById(id);
    }

    public Optional<User> user(Integer id) {
        return this.users.findById(id);
    }
}
