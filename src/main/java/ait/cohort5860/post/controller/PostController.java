package ait.cohort5860.post.controller;

import ait.cohort5860.post.service.PostService;
import ait.cohort5860.post.dto.NewCommentDto;
import ait.cohort5860.post.dto.NewPostDto;
import ait.cohort5860.post.dto.PostDto;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/forum")
public class PostController {

    private final PostService postService;

    @PostMapping("/post/{user}")
    @ResponseStatus(HttpStatus.CREATED)
    public PostDto addNewPost(@PathVariable String author, NewPostDto newPostDto) {
        return postService.addNewPost(author, newPostDto);
    }

    @GetMapping("/post{id}")
    public PostDto findPostById(@PathVariable long id) {
        return postService.findPostById(id);
    }

    @PatchMapping("/post/{id}/like")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addLike(@PathVariable long id) {

    }

    @PatchMapping("/post/{postId}/comment/{author}")
    public PostDto addComment(@PathVariable long postId, @PathVariable String author, NewCommentDto newCommentDto) {
        return postService.addComment(postId, author, newCommentDto);
    }

    @DeleteMapping("/post/{postId}")
    public PostDto deletePost(@PathVariable long postId) {
        return postService.deletePost(postId);
    }

    @PatchMapping("/post/{postId}")
    public PostDto updatePost(@PathVariable long postId, NewPostDto newPostDto) {
        return postService.updatePost(postId, newPostDto);
    }

    @GetMapping("/posts/tags")
    public List<PostDto> findPostsByTags(@RequestParam("values") Set<String> tags) {
        return postService.findPostsByTags(tags);
    }

    @GetMapping("/posts/period")
    public List<PostDto> findPostsByPeriod(@RequestParam("dateFrom") LocalDate startDate, @RequestParam("dateTo") LocalDate endDate) {
        return postService.findPostsByPeriod(startDate, endDate);
    }

    @GetMapping("/posts/author/{author}")
    public List<PostDto> findPostByAuthor(@PathVariable String author) {
        return postService.findPostByAuthor(author);
    }
}
