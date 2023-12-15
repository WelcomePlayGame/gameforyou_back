package game.you.dto;

import game.you.entity.GamePostRU;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GamePostVerticalDTORU {
    private Long id;
    private String poster_300x300;
}
