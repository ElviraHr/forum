package ait.cohort5860.post.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@EqualsAndHashCode(of="id")
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String message;
    private LocalDateTime dateCreated =  LocalDateTime.now();
    private  int likes;
    @ManyToOne //много комментов к одному посту
    @Setter
    private Post post;

    public Comment(String username, String message) {
        this.username = username;
        this.message = message;
    }

    public void addLLike(){
        likes++;
    }

    @Override
    public String toString() {
        return  String.format("[%s] %s : %s ", dateCreated, username, message);
    }
}
