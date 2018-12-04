package com.vince.exercise;

import java.util.Optional;

import com.coxautodev.graphql.tools.GraphQLResolver;

import org.springframework.stereotype.Component;

@Component
public class BookResolver implements GraphQLResolver<Book> {

    private UserRepository users;

    public BookResolver(UserRepository users) {
        this.users = users;
    }

    public Optional<User> getUser(Book book) {
        return users.findById(book.getUser().getId());
    }
}
