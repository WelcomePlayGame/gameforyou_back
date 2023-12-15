package game.you.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StatisticsArticlePL {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "action", columnDefinition = "bigint default 0")
    private long action;
    @Column(name = "more15", columnDefinition = "bigint default 0")
    private long more15;
    @Column(name = "more30",  columnDefinition = "bigint default 0")
    private long more30;
    @Column(name = "more45",  columnDefinition = "bigint default 0")
    private long more45;
    @OneToOne(mappedBy = "statistics")
    private ArticlePL articlePL;

}
