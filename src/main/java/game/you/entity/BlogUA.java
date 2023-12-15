package game.you.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;


@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class BlogUA {

    @Id
    @Column(name = "id")
    private long id;
    @Column(name = "title", unique = true)
    @NotNull
    @Size(min = 15, max = 75, message = "Title should be from 15 to 75 symbol")
    private String title;
    @Column(name = "des", unique = true)
    @NotNull
    @Size(min = 300, max = 10_000, message = "Descibe should be from 300 to 10000 symbol")
    private String des;
    @Column(name = "photo_Url")
    private String photo_Url;
    @Column(name = "type")
    private String type;

}
