package com.vince.exercise.elasticsearch;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import com.vince.exercise.Book;

@Document(indexName = "books", type = "book")
public class BookES {

    @Id
    private Integer id;

    private String title;

    private String summary;

    public BookES() {
        //
    }

    public BookES(Book book) {
        this.id = book.getId();
        this.title = book.getTitle();
        this.summary = book.getSummary();
    }

    public BookES(Integer id, String title, String summary) {
        this.id = id;
        this.title = title;
        this.summary = summary;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
