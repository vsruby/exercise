package com.vince.exercise;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.jpa.repository.Query;

@RepositoryRestResource(collectionResourceRel = "books", path = "books")
public interface BookRepository extends CrudRepository<Book, Integer> {
    List<Book> findByUserId(Integer id);
    List<Book> findByUserIdIsNotNullAndCheckedAtIsNotNull();
    List<Book> findByUserIdIsNullOrCheckedAtIsNull();
    List<Book> findByUserIdIsNotNullAndCheckedAtBefore(Date date);
}
