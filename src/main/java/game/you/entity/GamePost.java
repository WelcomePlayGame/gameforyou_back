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
public class GamePost {
    @Id
    @Column(name = "id")
    private long id;
    @Column(name = "title")
    private String title;
    @Column(name = "des")
    private String des;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gamepost_id")
    private Category category;
    @OneToMany(mappedBy = "gamePost")
    private Set<Article> getLsit = new LinkedHashSet<>();
}
