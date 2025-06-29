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
    @Column(name = "id")
    private long id;
    @Setter
    @Column(name = "title")
    private String title;
    @Setter
    @Column(name = "content")
    private String content;
    @Setter
    @Column(name = "author")
    private String author;
    @Column(name = "date_created")
    private LocalDateTime dateCreated = LocalDateTime.now();
    @Column(name = "likes")
    private int likes;
    //необходимо указать родительскую сущность. Издатель может быть без книги, но книга без издателя быть не может. Родительская сущность - Издательство (отношение родительская - дочерняя сущность)
    @ManyToMany
    @JoinTable(
            name = "posts_tags",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_name")

    )
    private Set<Tag> tags =  new HashSet<>();

    //указываем отношение между таблицами относительно главной таблицы, в одном посте - много комментов
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL) //поле в Комменте, с которым связано , fetch = fetchtype.eger = жадная загрузка, тащит сразу все. В цикле или стриме. Но лучше ставить над конкретным методом транзакшнал
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
