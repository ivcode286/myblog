package com.springboot.blog.payload;

import com.springboot.blog.entity.Post;
import lombok.Data;


@Data
public class CommentDto {
    private long id;
    private String name;
    private String email;
    private String body;

}
