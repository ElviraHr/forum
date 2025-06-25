package ait.cohort5860.post.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostDto {
    private long id;
    private String title;
    private String content;
    private String author;
    private LocalDateTime dateCreated;
    @Singular
    private Set<String> tags;
    private Integer likes;
    @Singular
    private List<String> comments;
}
