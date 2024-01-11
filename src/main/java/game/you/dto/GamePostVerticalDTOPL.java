package game.you.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class GamePostVerticalDTOPL implements Serializable {
    private Long id;
    private String poster_300x300;
}
