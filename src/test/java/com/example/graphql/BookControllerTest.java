package com.example.graphql;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.graphql.test.tester.GraphQlTester;

import static org.assertj.core.api.Assertions.assertThat;

@GraphQlTest(BookController.class)
class BookControllerTest {
    @Autowired
    private GraphQlTester graphQlTester;

    @Test
    @DisplayName("根据ID获取图书")
    void getBookById() {
        Book book = this.graphQlTester
                .documentName("bookDetails")
                .variable("id", "book-1")
                .execute()
                .path("bookById")
                .entity(Book.class)
                .get();

        assertThat(book.getId()).isEqualTo("book-1");
    }
}
