package gm.connectfour.service;

import gm.connectfour.domain.entity.Board;
import gm.connectfour.dto.Move;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public abstract class AbstractRefereeServiceMoveTest {

    protected final boolean isVictory;
    protected final Move move;
    protected final Board board;
    protected final int rowNumber;
    protected final RefereeService refereeService;

    public AbstractRefereeServiceMoveTest(boolean isVictory, int rowNumber, Move move, String strBoard) {
        this.isVictory = isVictory;
        this.rowNumber = rowNumber;
        this.board = Board.builder().build(strBoard);
        this.move = move;
        refereeService = new RefereeService();
    }

    protected String getMessage() {
        return String.format("Epecting %b for move (row=%d,col=%d) %s:\n%s", isVictory, rowNumber, move.getColumn(), move.getPlayer(),
                board.toString());
    }
}
