package game.you.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
public class CommentDTOPL {
    private String title_comment;
    private String des_comment;
    private Set<String> positiveInputs;
    private Set<String> negativeInputs;
    private String rating;
    private long id_post;
}
