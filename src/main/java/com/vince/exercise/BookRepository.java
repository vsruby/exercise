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

    @Query("SELECT b FROM Book b WHERE b.checkedAt IS NOT NULL AND b.member IS NOT NULL")
    List<Book> findAllCheckedOut();
    List<Book> findByMemberIdIsNotNullAndCheckedAtIsNotNull(); //Should be the same as the above method

    @Query("SELECT b FROM Book b WHERE b.id IN ?1 AND b.checkedAt IS NOT NULL AND b.member IS NOT NULL")
    List<Book> findAllCheckedOutWithIds(List<Integer> ids);

    @Query("SELECT b FROM Book b WHERE b.checkedAt < ?1 AND b.checkedAt IS NOT NULL AND b.member IS NOT NULL")
    List<Book> findAllOverdue(Date date);
    List<Book> findByMemberIdIsNotNullAndCheckedAtBefore(Date date); //Should be the same as the above method

    @Query("SELECT b FROM Book b WHERE b.id IN ?2 AND b.checkedAt < ?1 AND b.checkedAt IS NOT NULL AND b.member IS NOT NULL")
    List<Book> findAllOverdueWithIds(Date date, List<Integer> ids);

    @Query("SELECT b FROM Book b WHERE b.checkedAt IS NULL OR b.member IS NULL")
    List<Book> findAllAvailable();
    List<Book> findByMemberIdIsNullOrCheckedAtIsNull(); //Should be the same as the above method

    @Query("SELECT b FROM Book b WHERE (b.checkedAt IS NULL OR b.member IS NULL) AND b.id IN ?1")
    List<Book> findAllAvailableWithIds(List<Integer> ids);
}
