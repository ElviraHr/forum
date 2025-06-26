package ait.cohort5860.post.service;

import ait.cohort5860.post.dao.CommentRepository;
import ait.cohort5860.post.dao.PostRepository;
import ait.cohort5860.post.dao.TagRepository;
import ait.cohort5860.post.dto.NewCommentDto;
import ait.cohort5860.post.dto.NewPostDto;
import ait.cohort5860.post.dto.PostDto;
import ait.cohort5860.post.dto.exception.PostNotFoundException;
import ait.cohort5860.post.model.Comment;
import ait.cohort5860.post.model.Post;
import ait.cohort5860.post.model.Tag;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final TagRepository tagRepository;
    private final CommentRepository commentRepository;
    private final ModelMapper modelMapper;


    @Override
    @Transactional //all method executed in transaction
    public PostDto addNewPost(String author, NewPostDto newPostDto) {
        Post post = new Post(newPostDto.getTitle(), newPostDto.getContent(), author);
        Set<String> tags = newPostDto.getTags();
        //Handle tags
        if (tags != null) {
            for (String tagName : tags) {
                Tag tag = tagRepository.findById(tagName).orElseGet(
                        () -> tagRepository.save(new Tag(tagName))
                );
                post.addTag(tag);
            }
        }
        postRepository.save(post);
        return modelMapper.map(post, PostDto.class); //make DTO-obj
    }

    @Override
    public PostDto findPostById(long id) {
        Post post = postRepository.findById(id).orElseThrow(PostNotFoundException::new);
        return modelMapper.map(post, PostDto.class);
    }

    @Override
    @Transactional
    public void addLike(long postId) {
        Post post = postRepository.findById(postId).orElseThrow(PostNotFoundException::new);
        post.addLike();
    }

    @Override
    @Transactional
    public PostDto addComment(long postId, String author, NewCommentDto newCommentDto) {

        Post post = postRepository.findById(postId).orElseThrow(PostNotFoundException::new);

        Comment comment = new Comment(author, newCommentDto.getComment());

        post.addComment(comment);
        comment.setPost(post);

        commentRepository.save(comment);
        postRepository.save(post);

        return modelMapper.map(post, PostDto.class);
    }

    @Override
    @Transactional
    public PostDto deletePost(long postId) {
        Post post = postRepository.findById(postId).orElseThrow(PostNotFoundException::new);
        postRepository.delete(post);

        return modelMapper.map(post, PostDto.class);
    }

    @Override
    @Transactional
    public PostDto updatePost(long postId, NewPostDto newPostDto) {

        Post post = postRepository.findById(postId).orElseThrow(PostNotFoundException::new);
        post.setTitle(newPostDto.getTitle());
        post.setContent(newPostDto.getContent());
        if (newPostDto.getTags() != null) {
            for (String tagName : newPostDto.getTags()) {
                post.addTag(tagRepository.findById(tagName).orElseGet(
                        () -> tagRepository.save(new Tag(tagName))));
            }
        }
        postRepository.save(post);

        return  modelMapper.map(post, PostDto.class);
    }

    @Override
    public List<PostDto> findPostsByTags(Set<String> tags) {
        return List.of();
    }

    @Override
    public List<PostDto> findPostsByPeriod(LocalDate startDate, LocalDate endDate) {
        return List.of();
    }

    @Override
    public List<PostDto> findPostByAuthor(String author) {
        return List.of();
    }
}
