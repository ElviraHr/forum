package ait.cohort5860.post.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@EqualsAndHashCode(of = "name")
@NoArgsConstructor
@Entity
@Table(name = "tags")
public class Tag {
    @Id
    private String name;
    @ManyToMany(mappedBy = "tags")/// manyToMany достаточно поставить аннотацию только у одного участника связи, остальные с двух сторон выставляем
    private Set<Post> posts = new HashSet<>();

    public Tag(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
