package com.example.graphql;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GraphQlQuery {
    private String query;

    private Object variables;
}
