package com.vince.exercise;

import java.util.Date;
import java.util.List;

// import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@RepositoryRestResource(collectionResourceRel = "books", path = "books")
public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findByMemberId(Integer id);
    List<Book> findByMemberIdIsNotNullAndCheckedAtIsNotNull();
    List<Book> findByMemberIdIsNotNullAndCheckedAtBefore(Date date);

    @Query("SELECT b FROM Book b WHERE b.checkedAt IS NULL OR b.member IS NULL")
    List<Book> findAllAvailable();
    List<Book> findByMemberIdIsNullOrCheckedAtIsNull(); //Should be the same as the above method
}
