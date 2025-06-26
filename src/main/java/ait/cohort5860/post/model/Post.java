package ait.cohort5860.post.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Setter
    private String title;
    @Setter
    private String content;
    @Setter
    private String author;
    private LocalDateTime dateCreated = LocalDateTime.now();
    //необходимо указать родительскую сущность. Издатель может быть без книги, но книга без издателя быть не может. Родительская сущность - Издательство (отношение родительская - дочерняя сущность)
    @ManyToMany
    private Set<Tag> tags =  new HashSet<>();
    private int likes;
    //указываем отношение между таблицами относительно главной таблицы, в одном посте - много комментов
    @OneToMany(mappedBy = "post") //поле в Комменте, с которым связано
    private List<Comment> comments =  new ArrayList<>();


    public Post(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    public void addLike() {
        likes++;
    }

    public boolean addTag(Tag tag) {
        return tags.add(tag);
    }
}
