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
public class UserController {

    @Autowired
    private UserRepository users;

    @GetMapping("/users/{id}/books")
    public @ResponseBody Iterable<Book> getBooks(@PathVariable Integer id) {
        Optional<User> result = users.findById(id);

        if (result.isPresent()) {
            User user = result.get();

            return user.getBooks();
        } else {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Could not find user with id: [" + id + "]");
        }
    }
}
