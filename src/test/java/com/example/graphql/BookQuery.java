package com.example.graphql;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookQuery {
    private String id;
}
