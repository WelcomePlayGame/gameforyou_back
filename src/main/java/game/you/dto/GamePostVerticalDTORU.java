package game.you.dto;

import game.you.entity.GamePostRU;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class GamePostVerticalDTORU implements Serializable {
    private Long id;
    private String poster_300x300;
}
