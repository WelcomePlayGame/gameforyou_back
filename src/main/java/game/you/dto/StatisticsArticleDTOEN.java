package game.you.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
public class StatisticsArticleDTOEN implements Serializable  {
    private Long id;
    private long action;
    private long more15;
    private long more30;
    private long more45;
}
