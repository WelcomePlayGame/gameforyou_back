package game.you.dto;

import game.you.entity.GamePostRU;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GamePostHorizontalDTORU {

    private Long id;
    private String poster_1024x768;
    private String poster_480x320;

}
