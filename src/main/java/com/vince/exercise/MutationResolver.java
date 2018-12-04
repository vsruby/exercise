package com.vince.exercise;

import java.util.Date;
import java.util.Optional;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;

import org.springframework.stereotype.Component;

@Component
public class MutationResolver implements GraphQLMutationResolver {

    private BookRepository books;
    private MemberRepository members;

    public MutationResolver(BookRepository books, MemberRepository members) {
        this.books = books;
        this.members = members;
    }

    public Book newBook(String title, String summary) {
        Book book = new Book();

        book.setTitle(title);
        book.setSummary(summary);

        this.books.save(book);

        return book;
    }

    public Member newMember(String email, String name) {
        Member member = new Member();

        member.setEmail(email);
        member.setName(name);

        this.members.save(member);

        return member;
    }

    public boolean deleteBook(Integer id) {
        this.books.deleteById(id);
        return true;
    }

    public boolean deleteMember(Integer id) {
        Optional<Member> result = this.members.findById(id);

        result.ifPresent(member -> {
            member.getBooks().forEach(book -> {
                book.setCheckedAt(null);
                book.setMember(null);

                this.books.save(book);
            });

            this.members.delete(member);
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

    public Member updateMember(Integer id, String email, String name) {
        Member member = this.members.findById(id).orElseThrow(() -> new MemberNotFoundException("Could not find member with given id", id));

        member.setEmail(email);
        member.setName(name);

        this.members.save(member);

        return member;
    }

    public Book checkInBook(Integer id) {
        Book book = this.books.findById(id).orElseThrow(() -> new BookNotFoundException("Could not find book with given id", id));

        book.setCheckedAt(null);
        book.setMember(null);

        this.books.save(book);

        return book;
    }

    public Book checkOutBook(Integer id, Integer memberId) {
        Book book = this.books.findById(id).orElseThrow(() -> new BookNotFoundException("Could not find book with given id", id));
        Member member = this.members.findById(memberId).orElseThrow(() -> new MemberNotFoundException("Could not find member with given id", memberId));

        if (member.getLockedAt() != null) {
            throw new MemberRevokedException("Member with the given id cannot check out books.", memberId);
        }

        if (book.getMember() != null && book.getCheckedAt() != null) {
            throw new BookNotAvailableException("Book with the given id has already been checked out.", id);
        }

        book.setCheckedAt(new Date());
        book.setMember(member);

        this.books.save(book);

        return book;
    }

    public Member revokeMember(Integer id) {
        Member member = this.members.findById(id).orElseThrow(() -> new MemberNotFoundException("Could not find member with given id", id));

        member.setLockedAt(new Date());

        this.members.save(member);

        return member;
    }

    public Member reinstateMember(Integer id) {
        Member member = this.members.findById(id).orElseThrow(() -> new MemberNotFoundException("Could not find member with given id", id));

        member.setLockedAt(null);

        this.members.save(member);

        return member;
    }
}
