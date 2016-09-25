package gm.connectfour.dto;

import gm.connectfour.domain.entity.Player;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Move {
    private int column;
    private Player player;
}
