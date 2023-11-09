package com.example.graphql;

import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.Arrays;
import java.util.List;

/**
 * 图书Controller
 *
 * @author zhoujianhui
 * @date 2023-11-09 02:30:34
 * @lastModifiedBy zhoujianhui
 * @lastModifiedDate 2023-11-09 02:30:34
 */
@Controller
@AllArgsConstructor
public class BookController {
    @QueryMapping
    public Book bookById(@Argument String id) {
        List<Book> books = Arrays.asList(
                new Book("book-1", "Effective Java", 416, "author-1"),
                new Book("book-2", "Hitchhiker's Guide to the Galaxy", 208, "author-2"),
                new Book("book-3", "Down Under", 436, "author-3")
        );

        return books.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @SchemaMapping
    public Author author(Book book) {
        List<Author> authors = Arrays.asList(
                new Author("author-1", "Joshua", "Bloch"),
                new Author("author-2", "Douglas", "Adams"),
                new Author("author-3", "Bill", "Bryson")
        );

        return authors.stream()
                .filter(author -> author.getId().equals(book.getAuthorId()))
                .findFirst()
                .orElse(null);
    }
}