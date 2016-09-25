package gm.connectfour.domain.entity;

import org.junit.Test;

import java.util.Arrays;

import static gm.connectfour.domain.entity.Player.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class BoardBuilderTest {
    @Test
    public void should_create_empty_board_when_given_dimensions() {
        //given
        final int height = 3, width = 6;
        //when
        final Board board = Board.builder().height(height).width(width).build();
        //then
        assertThat(board, notNullValue());
        assertThat(board.getCells(), arrayWithSize(height));
        Arrays.stream(board.getCells()).forEach(line -> {
            assertThat(line, notNullValue());
            assertThat(line, arrayWithSize(width));
            Arrays.stream(line).forEach(cell -> {
                assertThat(board.toString(), cell, is(NONE));
            });
        });
    }

    @Test
    public void should_return_top_row_when_board_is_not_null(){
        //given
        final Board board = Board.builder().height(10).width(5).build();
        //when
        final Player[] row = board.getTopRow();
        //then
        assertThat(row, is(board.getCells()[0]));
    }

    @Test
    public void should_return_correct_board_when_string_representation_is_correct() {
        //given
        final String strBoard =
                ".ox\n" +
                "xxo\n" +
                "oxo\n" +
                "oox\n";
        final Player[][] expectedBoard = {
                {NONE, O, X},
                {X, X, O},
                {O, X, O},
                {O, O, X}
        };
        //when
        final Board board = Board.builder().build(strBoard);
        //then
        assertThat(board.getCells(), is(expectedBoard));
        assertThat(board.getWidth(), is(3));
        assertThat(board.getHeight(), is(4));
    }
}