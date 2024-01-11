package game.you.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class TagDTORU implements Serializable {
    private long id;
    private String title;
}
