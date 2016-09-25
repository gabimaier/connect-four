package gm.connectfour.service;

import gm.connectfour.domain.entity.Board;
import gm.connectfour.domain.entity.Player;
import gm.connectfour.domain.entity.GameState;
import gm.connectfour.dto.Move;
import gm.connectfour.exception.IllegalMoveException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

import static gm.connectfour.domain.entity.Player.NONE;
import static gm.connectfour.exception.IllegalMoveException.Code.COLUMN_FULL;
import static gm.connectfour.exception.IllegalMoveException.Code.OUT_OF_BOUNDS;

@Service
public class RefereeService {

    public static final int CONNECTED_TARGET = 4;

    public GameState evaluateMove(Board board, Move move) {
        if(isColumnOutOfBounds(board, move)){
            throw new IllegalMoveException(OUT_OF_BOUNDS);
        }
        if(isColumnFull(board, move)){
            throw new IllegalMoveException(COLUMN_FULL);
        }
        if(isWinningMove(board, move)){
            return GameState.VICTORY;
        }
        if(isLastPossibleMove(board, move)){
            return GameState.DRAW;
        }
        return GameState.NOT_FINISHED;
    }

    boolean isWinningMove(Board board, Move move) {
        final int rowNumber = getRowNumber(board, move);

        if (isWinningLine(board, move, rowNumber)) {
            return true;
        }
        if (isWinningColumn(board, move, rowNumber)) {
            return true;
        }
        if (isWinningDiagonalToNE(board, move, rowNumber)) {
            return true;
        }
        if (isWinningDiagonalToNW(board, move, rowNumber)) {
            return true;
        }

        //TODO test
        return false;
    }

    boolean isWinningLine(Board board, Move move, int rowNumber) {
        final int columnNumber = move.getColumn();
        final Player[][] cells = board.getCells();
        int connected = 1;
        int i = columnNumber - 1;
        while (i>=0 && cells[rowNumber][i]==move.getPlayer()){
            connected++;
            if(connected == CONNECTED_TARGET){
                return true;
            }
            i--;
        }
        i = columnNumber + 1;
        while (i<board.getWidth() && cells[rowNumber][i]==move.getPlayer()){
            connected++;
            if(connected == CONNECTED_TARGET){
                return true;
            }
            i++;
        }
        return false;
    }

    boolean isWinningColumn(Board board, Move move, int rowNumber) {
        final int columnNumber = move.getColumn();
        final Player[][] cells = board.getCells();
        int connected = 1;
        int i = rowNumber + 1;
        while (i<board.getHeight() && cells[i][columnNumber]==move.getPlayer()){
            connected++;
            if(connected == CONNECTED_TARGET){
                return true;
            }
            i++;
        }
        return false;
    }

    boolean isWinningDiagonalToNE(Board board, Move move, int rowNumber) {
        final int columnNumber = move.getColumn();
        final Player[][] cells = board.getCells();
        int connected = 1;
        int crtRow = rowNumber - 1;
        int crtCol = columnNumber - 1;
        while (crtCol>=0 && crtRow>=0 && cells[crtRow][crtCol]==move.getPlayer()){
            connected++;
            if(connected == CONNECTED_TARGET){
                return true;
            }
            crtRow--;
            crtCol--;
        }
        crtRow = rowNumber + 1;
        crtCol = columnNumber + 1;
        while (crtCol<board.getWidth() && crtRow<board.getHeight() && cells[crtRow][crtCol]==move.getPlayer()){
            connected++;
            if(connected == CONNECTED_TARGET){
                return true;
            }
            crtRow++;
            crtCol++;
        }
        return false;
    }

    boolean isWinningDiagonalToNW(Board board, Move move, int rowNumber) {
        final int columnNumber = move.getColumn();
        final Player[][] cells = board.getCells();
        int connected = 1;
        int crtRow = rowNumber - 1;
        int crtCol = columnNumber + 1;
        while (crtCol<board.getWidth() && crtRow>=0 && cells[crtRow][crtCol]==move.getPlayer()){
            connected++;
            if(connected == CONNECTED_TARGET){
                return true;
            }
            crtRow--;
            crtCol++;
        }
        crtRow = rowNumber + 1;
        crtCol = columnNumber - 1;
        while (crtCol>=0 && crtRow<board.getHeight() && cells[crtRow][crtCol]==move.getPlayer()){
            connected++;
            if(connected == CONNECTED_TARGET){
                return true;
            }
            crtRow++;
            crtCol--;
        }
        return false;
    }

    int getRowNumber(Board board, Move move) {
        final int columnNumber = move.getColumn();
        assert board.getTopRow()[columnNumber]== NONE;
        final Player[][] cells = board.getCells();
        int rowNumber = 0;
        while (rowNumber<cells.length && cells[rowNumber][columnNumber]== NONE){
            rowNumber++;
        }
        return rowNumber - 1;
    }

    boolean isLastPossibleMove(Board board, Move move) {
        return
        Arrays.stream(board.getTopRow()).filter(cell -> cell == NONE).limit(2).count() <= 1;
    }

    private boolean isColumnOutOfBounds(Board board, Move move) {
        return move.getColumn()<0 || move.getColumn()>board.getWidth();
    }

    private boolean isColumnFull(Board board, Move move) {
        return board.getTopRow()[move.getColumn()]!= NONE;
    }
}
