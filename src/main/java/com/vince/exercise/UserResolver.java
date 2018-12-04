package com.vince.exercise;

import java.util.List;

import com.coxautodev.graphql.tools.GraphQLResolver;

import org.springframework.stereotype.Component;

@Component
public class UserResolver implements GraphQLResolver<User> {

    private BookRepository books;

    public UserResolver(BookRepository books) {
        this.books = books;
    }

    public List<Book> getBooks(User user) {
        return books.findByUserId(user.getId());
    }
}
