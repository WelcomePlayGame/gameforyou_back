package game.you.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

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
    @Column(name = "type")
    private String type;

}
