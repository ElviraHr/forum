package ait.cohort5860.post.dao;

import ait.cohort5860.post.model.Post;
import ait.cohort5860.post.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;
import java.util.stream.Stream;

public interface PostRepository extends JpaRepository<Post, Long> {

    Stream<Post> findByAuthorIgnoreCase(String author);

    Stream<Post> findDistinctByTagsNameInIgnoreCase(Set<String> tags);

    Stream<Post> findByDateCreatedBetween(LocalDateTime from, LocalDateTime to);
}
