package com.vince.exercise;

import java.util.Optional;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.hateoas.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.client.HttpClientErrorException;

@RestController
public class BookController {

    @Autowired
    private BookRepository books;

    @GetMapping("/books/{id}/member")
    public @ResponseBody Member getMember(@PathVariable Integer id) {
        Optional<Book> result = books.findById(id);

        if (result.isPresent()) {
            Book book = result.get();

            return book.getMember();
        } else {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Could not find book with id: [" + id + "]");
        }
    }
}
