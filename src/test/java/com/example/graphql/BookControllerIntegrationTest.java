package com.example.graphql;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@DisplayName("EbayAuthController")
class BookControllerIntegrationTest {
    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
        RestAssured.basePath = "/graphql";
    }

    @Test
    @DisplayName("根据ID获取图书")
    void getBookByIdUsingRestAssured() {
        GraphQlQuery graphQlQuery = GraphQlQuery.builder()
                .query("query bookDetails($id:ID!){   bookById(id:$id) {     id     name     pageCount     author {       id       firstName       lastName     }   } }")
                .variables(BookQuery.builder()
                        .id("book-1")
                        .build())
                .build();

        // @formatter:off
        given()
                .contentType(ContentType.JSON)
                .body(graphQlQuery).
        when()
                .post("/").
        then()
                .statusCode(HttpStatus.OK.value())
                .log()
                .all();
        // @formatter:on
    }

    @Test
    @DisplayName("根据ID获取图书")
    void getBookByIdUsingGraphQlClient() {
        WebClient client = WebClient.builder()
                .baseUrl("http://localhost:" + port + "/graphql")
                .build();
        HttpGraphQlClient graphQlClient = HttpGraphQlClient.builder(client).build();

        Mono<Book> bookMono = graphQlClient.documentName("bookDetails")
                .variable("id", "book-1")
                .retrieve("bookById")
                .toEntity(Book.class);

        StepVerifier.create(bookMono)
                .assertNext(book -> assertThat(book.getId()).isEqualTo("book-1"))
                .verifyComplete();

        /*Book book = graphQlClient.documentName("bookDetails")
                .variable("id", "book-1")
                .retrieve("bookById")
                .toEntity(Book.class)
                .block();

        assertThat(book.getId()).isEqualTo("book-1");*/
    }
}
