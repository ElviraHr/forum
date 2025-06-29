package ait.cohort5860.post.service;

import ait.cohort5860.post.dto.NewCommentDto;
import ait.cohort5860.post.dto.NewPostDto;
import ait.cohort5860.post.dto.PostDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface PostService {

    PostDto addNewPost(String author, NewPostDto newPostDto);
    PostDto findPostById(long id);
    void addLike(long postId);
    PostDto addComment(long  postId, String author, NewCommentDto newCommentDto);
    PostDto deletePost(long  postId);
    PostDto updatePost(long  postId, NewPostDto newPostDto);
    List<PostDto> findPostsByTags(Set<String> tags);
    List<PostDto> findPostsByPeriod(LocalDate startDate, LocalDate endDate);
    List<PostDto> findPostByAuthor(String author);
}
