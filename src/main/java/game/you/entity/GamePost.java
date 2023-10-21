package game.you.entity;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.rest.core.annotation.RestResource;

@Entity
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table
@RestResource(rel = "gamepost", path = "gamepost")
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
}
