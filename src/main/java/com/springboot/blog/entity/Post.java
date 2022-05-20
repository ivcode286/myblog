package com.springboot.blog.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

//@Data  use @Getter and @Setter instead to omit toString(),in case of ModelMapper stackoverflow error
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(
        name = "posts",uniqueConstraints = {@UniqueConstraint(columnNames={"title"})}
)
public class Post {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    @Column (name="title",nullable = false)
    private String title;

    @Column (name="description",nullable = false)
    private String description;

    @Column (name="content",nullable = false)
    private String content;

    //CascadeType.All ,when we update the parent,we would like to update the children either
    //orphanRemoval delete as well
    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<Comment> comments = new HashSet<>();

}
