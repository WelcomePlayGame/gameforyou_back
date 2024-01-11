package game.you.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class GamePostHorizontalDTOUA implements Serializable {
    private long id;
    private String poster_1024x768;
    private String poster_480x320;
}
