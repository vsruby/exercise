package com.vince.exercise;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "books", path = "books")
public interface BookRepository extends CrudRepository<Book, Integer> {
    List<Book> findByUserId(Integer id);
    List<Book> findByUserIdIsNotNullAndCheckedAtIsNotNull();
}
