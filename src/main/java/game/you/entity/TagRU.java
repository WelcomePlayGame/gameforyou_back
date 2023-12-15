package game.you.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;


@Entity
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TagRU {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "title")
    private String title;
    @ManyToMany(mappedBy = "tagSet")
    private Set<ArticleRU> article  = new LinkedHashSet<>();
}