package game.you.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.rest.core.annotation.RestResource;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RestResource(rel = "category", path = "category")
public class Category {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "title")
    private String title;
    @OneToMany(mappedBy = "category")
    private Set<Article> articleList = new LinkedHashSet<>();
    @OneToMany(mappedBy = "category")
    private Set<Blog> blogList = new LinkedHashSet<>();
}
