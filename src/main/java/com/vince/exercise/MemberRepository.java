package com.vince.exercise;

import java.util.Date;
import java.util.List;

// import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@RepositoryRestResource(collectionResourceRel = "members", path = "members")
public interface MemberRepository extends JpaRepository<Member, Integer> {

    @Query("SELECT m FROM Member m WHERE (SELECT count(b) FROM Book b WHERE b.member = m.id AND b.checkedAt < ?1 AND b.checkedAt IS NOT NULL) > 0")
    List<Member> findAllWithOverdueBooks(Date date);
}
