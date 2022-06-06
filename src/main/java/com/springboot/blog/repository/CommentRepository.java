package com.springboot.blog.repository;

import com.springboot.blog.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//don't need @Repository as it extends SimpleJpaRepository which has stated @Repository and @Transactional
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByPostId(long postId);

}
