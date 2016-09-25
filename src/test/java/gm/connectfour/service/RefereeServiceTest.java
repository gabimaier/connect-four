package gm.connectfour.service;

import gm.connectfour.domain.entity.Board;
import gm.connectfour.domain.entity.Player;
import gm.connectfour.dto.Move;
import gm.connectfour.exception.IllegalMoveException;
import org.junit.Test;

import java.util.Arrays;

import static gm.connectfour.domain.entity.Player.*;
import static gm.connectfour.exception.IllegalMoveException.Code.COLUMN_FULL;
import static gm.connectfour.exception.IllegalMoveException.Code.OUT_OF_BOUNDS;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class RefereeServiceTest {
    private final RefereeService refereeService = new RefereeService();

    @Test
    public void should_do_nothing_when_move_is_valid(){
        //given
        final Move move = Move.builder().column(1).player(X).build();
        final Board board = Board.builder().height(10).width(5).build();
        //when
        refereeService.evaluateMove(board, move);
        //then
    }

    @Test(expected = IllegalMoveException.class)
    public void should_throw_out_of_bounds_when_move_is_on_invalid_column(){
        //given
        final Move move = Move.builder().column(100).player(X).build();
        final Board board = Board.builder().height(10).width(5).build();
        //when
        try {
            refereeService.evaluateMove(board, move);
        }catch (IllegalMoveException e){
            //then
            assertThat(e.getReason(), is(OUT_OF_BOUNDS));
            throw e;
        }
    }

    @Test(expected = IllegalMoveException.class)
    public void should_throw_column_full_when_move_is_on_full_column(){
        //given
        final int playedColumn = 2;
        final Move move = Move.builder().column(playedColumn).player(X).build();
        final Board board = Board.builder().height(10).width(5).build();
        Arrays.stream(board.getCells()).forEach(row -> {
            row[playedColumn] = X;
        });
        //when
        try {
            refereeService.evaluateMove(board, move);
        }catch (IllegalMoveException e){
            //then
            assertThat(e.getReason(), is(COLUMN_FULL));
            throw e;
        }
    }

    @Test
    public void should_return_true_when_game_is_a_draw(){
        //given
        int playedColumn = 1;
        final Move move = Move.builder().column(playedColumn).player(X).build();
        final String[] strBoards = {
                ". o x\n" +
                "x x o\n" +
                "o x o"};
        final Player[][] cellStates = {
                {NONE, O, X},
                {X, X, O},
                {O, X, O},
        };
        final Board board = Board.builder().build(cellStates);
        //when
        final boolean isDraw = refereeService.isLastPossibleMove(board, move);
        //then
        assertThat(board.toString(), isDraw, is(true));
    }

    @Test
    public void should_return_row_number_when_board_is_not_full() {
        //given
        final Player[][] cellStates = {
                {NONE, NONE, X},
                {X, NONE, O},
                {O, NONE, O},
        };
        final Board board = Board.builder().build(cellStates);
        //when
        final int onColumn0 = refereeService.getRowNumber(board, Move.builder().column(0).player(X).build());
        final int onColumn1 = refereeService.getRowNumber(board, Move.builder().column(1).player(X).build());
        //then
        assertThat(onColumn0, is(0));
        assertThat(onColumn1, is(2));
    }

    @Test
    public void should_return_correct_string_representation(){
        //given
        final Player[][] cellStates = {
                {NONE, O, X},
                {X, X, O},
                {O, X, O},
        };
        final Board board = Board.builder().build(cellStates);
        //when
        final String strBoard = board.toString();
        //then
        assertThat(strBoard, is(
            ".ox\n" +
            "xxo\n" +
            "oxo\n"
        ) );
    }

    @Test
    public void should_deserialize_correctly(){
        //given
        final Player[][] cellStates = {
                {NONE, O, X},
                {X, X, O},
                {O, X, O},
        };
        final Board initialBoard = Board.builder().build(cellStates);
        final String strBoard = initialBoard.toString();
        //when
        final Board actualBoard = Board.builder().build(strBoard);
        //then
        assertThat(actualBoard, is(initialBoard) );
    }

    @Test
    public void should_serialize_correctly() {
        //given
        final String strBoard = ".ox\n" +
                "xxo\n" +
                "oxo\n";
        final Board board = Board.builder().build(strBoard);
        //when
        final String returnedStringBoard = board.toString();
        //then
        assertThat(returnedStringBoard, is(strBoard));
    }
}
