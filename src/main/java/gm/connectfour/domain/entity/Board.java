package gm.connectfour.domain.entity;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

import static gm.connectfour.domain.entity.Player.NONE;

@Getter
@Setter(AccessLevel.NONE)
@EqualsAndHashCode
public class Board {
    private Player[][] cells;
    private int height;
    private int width;



    public static class Builder{
        private Board board = new Board();

        public Board.Builder height(int height){
            board.height = height;
            return this;
        }

        public Board.Builder width(int width){
            board.width = width;
            return this;
        }

        public Board build(){
            board.cells = new Player[board.height][];
            for (int i = 0; i < board.height; i++) {
                board.cells[i] = new Player[board.width];
                Arrays.fill(board.cells[i], NONE);
            }
            return board;
        }

        public Board build(String strBoard){
            if(strBoard == null || "".equals(strBoard)){
                return null;
            }
            final String[] rows = strBoard.split("\n");
            if(rows.length==0){
                throw new IllegalArgumentException(strBoard);
            }
            board.width = rows[0].length();
            board.height = rows.length;
            board.cells = new Player[board.height][];
            for (int i = 0; i < rows.length; i++) {
                board.cells[i] = new Player[board.width];
                final char[] rowChars = rows[i].toCharArray();
                for (int j = 0; j < rowChars.length; j++) {
                    board.cells[i][j] = Player.valueOf(rowChars[j]);
                }
            }
            return board;
        }

        public Board build(Player[][] cellStates){
            if(cellStates.length == 0){
                throw new IllegalArgumentException("empty array");
            }
            board.height = cellStates.length;
            board.width = cellStates[0].length;
            board.cells = cellStates;
            return board;
        }
    }

    public static Board.Builder builder(){
        return new Board.Builder();
    }

    public Player[] getTopRow() {
        return cells[0];
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        for(Player[] row : cells){
            for (Player cell : row){
                builder.append(cell);
            }
            builder.append("\n");
        }
        return builder.toString();
    }
}
