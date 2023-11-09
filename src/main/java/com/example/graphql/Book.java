package com.example.graphql;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 图书
 *
 * @author zhoujianhui
 * @date 2023-11-09 02:30:11
 * @lastModifiedBy zhoujianhui
 * @lastModifiedDate 2023-11-09 02:30:11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    String id;

    String name;

    int pageCount;

    String authorId;
}
