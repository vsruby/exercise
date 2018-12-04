package com.vince.exercise;

import java.util.Date;
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

    public Book updateBook(Integer id, String title, String summary) {
        Book book = this.books.findById(id).orElseThrow(() -> new BookNotFoundException("Could not find book with given id", id));

        book.setTitle(title);
        book.setSummary(summary);

        this.books.save(book);

        return book;
    }

    public User updateUser(Integer id, String email, String name) {
        User user = this.users.findById(id).orElseThrow(() -> new UserNotFoundException("Could not find user with given id", id));

        user.setEmail(email);
        user.setName(name);

        this.users.save(user);

        return user;
    }

    public Book checkInBook(Integer id) {
        Book book = this.books.findById(id).orElseThrow(() -> new BookNotFoundException("Could not find book with given id", id));

        book.setCheckedAt(null);
        book.setUser(null);

        this.books.save(book);

        return book;
    }

    public Book checkOutBook(Integer id, Integer userId) {
        Book book = this.books.findById(id).orElseThrow(() -> new BookNotFoundException("Could not find book with given id", id));
        User user = this.users.findById(userId).orElseThrow(() -> new UserNotFoundException("Could not find user with given id", userId));

        book.setCheckedAt(new Date());
        book.setUser(user);

        this.books.save(book);

        return book;
    }

    public User revokeUser(Integer id) {
        User user = this.users.findById(id).orElseThrow(() -> new UserNotFoundException("Could not find user with given id", id));

        user.setLockedAt(new Date());

        this.users.save(user);

        return user;
    }

    public User reinstateUser(Integer id) {
        User user = this.users.findById(id).orElseThrow(() -> new UserNotFoundException("Could not find user with given id", id));

        user.setLockedAt(null);

        this.users.save(user);

        return user;
    }
}
