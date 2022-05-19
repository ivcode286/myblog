package com.springboot.blog.respository;

import com.springboot.blog.entity.Comment;
import com.springboot.blog.payload.CommentDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//don't need @Repository as it extends SimpleJpaRepository which has stated @Repository and @Transactional
public interface CommentRepository extends JpaRepository<Comment,Long> {

    List<Comment> findByPostId(long postId);

}
