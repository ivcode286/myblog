package com.springboot.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name="comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //if no  @Column (name=?) stated ,JPA will consider the table fields name are same as variable name
    private String name;
    private String email;
    private String body;

    //Many Comments to One Post
    //FetchType.LAZY tells Hibernate to only fetch the related entities
    //@JoinColumn for foreign key
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="post_id",nullable=false)
    private Post post;
}
