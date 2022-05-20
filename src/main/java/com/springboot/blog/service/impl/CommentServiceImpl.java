package com.springboot.blog.service.impl;

import com.springboot.blog.entity.Comment;
import com.springboot.blog.entity.Post;
import com.springboot.blog.exception.BlogAPIException;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.payload.CommentDto;
import com.springboot.blog.respository.CommentRepository;
import com.springboot.blog.respository.PostRepository;
import com.springboot.blog.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private PostRepository postRepository;

    public CommentServiceImpl(CommentRepository commentRepository,PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    @Override
    public CommentDto createComment(Long postId, CommentDto commentDto) {
        Comment comment = mapToEntity(commentDto);

        Post post = postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","id",postId));
        comment.setPost(post);

        //save comment entity to DB
        Comment newComment =commentRepository.save(comment);

        return mapToDto(newComment);
    }

    @Override
    public List<CommentDto> getCommentsByPostId(long postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);

        //convert list of comment entities to list of commentDto
        return comments.stream().map(comment->mapToDto(comment)).collect(Collectors.toList());
    }

    @Override
    public CommentDto getCommentById(Long postId, Long commentId) {
        Post post = postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","id",postId));
        Comment comment = commentRepository.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment","id",commentId));

        if (!comment.getPost().getId().equals((post.getId()))){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Comment does not belong to post");
        }
        return mapToDto(comment);
    }

    @Override
    public CommentDto updateComment(Long postId, Long commentId, CommentDto commentRequest) {
        Post post = postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","id",postId));
        Comment comment = commentRepository.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment","id",commentId));
        if (!comment.getPost().getId().equals((post.getId()))){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Comment does not belong to post");
        }
        comment.setName(commentRequest.getName());
        comment.setEmail(commentRequest.getEmail());
        comment.setBody(commentRequest.getBody());

        Comment updatedComment = commentRepository.save(comment);

        return mapToDto(updatedComment);
    }

    @Override
    public void deleteComment(Long postId, Long commentId) {
        Post post = postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","id",postId));
        Comment comment = commentRepository.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment","id",commentId));
        if (!comment.getPost().getId().equals((post.getId()))){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Comment does not belong to post");
        }
         commentRepository.delete(comment);
    }

    private CommentDto mapToDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setBody(comment.getBody());
        commentDto.setEmail(comment.getEmail());
        commentDto.setName(comment.getName());
        return commentDto;
    }

    private Comment mapToEntity(CommentDto commentDto){
        Comment comment = new Comment();
        comment.setBody(commentDto.getBody());
        comment.setEmail(commentDto.getEmail());
        comment.setName(commentDto.getName());
        comment.setId(commentDto.getId());
        return comment;
    }

}
