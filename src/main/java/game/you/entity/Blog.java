package game.you.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.rest.core.annotation.RestResource;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RestResource(rel = "blog", path = "blog")
public class Blog {

    @Id
    @Column(name = "id")
    private long id;
    @Column(name = "title")
    private String title;
    @Column(name = "des")
    private String des;
    @Column(name = "photo_Url")
    private String photo_Url;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "blog_id")
    private Category category;

}
