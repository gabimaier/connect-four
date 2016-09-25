package gm.connectfour.domain.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Game {
    private String id;
    private Board board;
    private String lastColour;
    private boolean deleted = false;
    private GameState state;

    public String getId() {
        return id;
    }
}
