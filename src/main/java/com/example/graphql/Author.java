package com.example.graphql;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 图书作者
 *
 * @author zhoujianhui
 * @date 2023-11-09 02:26:07
 * @lastModifiedBy zhoujianhui
 * @lastModifiedDate 2023-11-09 02:26:07
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Author {
    String id;

    String firstName;

    String lastName;
}