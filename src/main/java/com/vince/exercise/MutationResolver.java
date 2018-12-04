package com.vince.exercise;

import java.util.Optional;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;

import org.springframework.stereotype.Component;

@Component
public class MutationResolver implements GraphQLMutationResolver {

    private BookRepository books;
    private UserRepository users;

    public MutationResolver(BookRepository books, UserRepository users) {
        this.books = books;
        this.users = users;
    }

    public Book newBook(String title, String summary) {
        Book book = new Book();

        book.setTitle(title);
        book.setSummary(summary);

        this.books.save(book);

        return book;
    }

    public User newUser(String email, String name) {
        User user = new User();

        user.setEmail(email);
        user.setName(name);

        this.users.save(user);

        return user;
    }

    public boolean deleteBook(Integer id) {
        this.books.deleteById(id);
        return true;
    }

    public boolean deleteUser(Integer id) {
        Optional<User> result = this.users.findById(id);

        result.ifPresent(user -> {
            user.getBooks().forEach(book -> {
                book.setCheckedAt(null);
                book.setUser(null);

                this.books.save(book);
            });

            this.users.delete(user);
        });

        return true;
    }
}
