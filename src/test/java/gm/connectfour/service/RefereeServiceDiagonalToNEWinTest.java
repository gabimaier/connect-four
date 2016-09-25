package gm.connectfour.service;

import gm.connectfour.domain.entity.Player;
import gm.connectfour.dto.Move;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.Matchers.is;

public class RefereeServiceDiagonalToNEWinTest extends AbstractRefereeServiceMoveTest {
    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {true, 3, Move.builder().column(3).player(Player.X).build(),
                    //   012345
                        "x.....\n" +    //0
                        "ox....\n" +    //1
                        "oox...\n" +    //2
                        "ooo.oo\n" +    //3
                        "oooooo\n"      //4
                },
                {true, 3, Move.builder().column(3).player(Player.X).build(),
                    //   012345
                        "......\n" +    //0
                        "ox....\n" +    //1
                        "oox...\n" +    //2
                        "ooo.oo\n" +    //3
                        "ooooxo\n"      //4
                },
                {true, 2, Move.builder().column(2).player(Player.X).build(),
                    //   012345
                        "x.....\n" +    //0
                        "ox....\n" +    //1
                        "oo....\n" +    //2
                        "oooxoo\n" +    //3
                        "ooooxo\n"      //4
                },
                {true, 1, Move.builder().column(1).player(Player.X).build(),
                    //   012345
                        "......\n" +    //0
                        "o.....\n" +    //1
                        "oox...\n" +    //2
                        "oooxoo\n" +    //3
                        "ooooxo\n"      //4
                },
                {true, 0, Move.builder().column(0).player(Player.X).build(),
                    //   012345
                        "......\n" +    //0
                        "ox....\n" +    //1
                        "oxx...\n" +    //2
                        "oooxoo\n" +    //3
                        "oooooo\n"      //4
                },
                {false, 0, Move.builder().column(0).player(Player.X).build(),
                    //   012345
                        "......\n" +    //0
                        "ox....\n" +    //1
                        "oxx...\n" +    //2
                        "oooooo\n" +    //3
                        "oooooo\n"      //4
                }
        });
    }

    public RefereeServiceDiagonalToNEWinTest(boolean expected, int rowNumber, Move move, String strBoard) {
        super(expected, rowNumber, move, strBoard);
    }

    @Test
    public void should_recognize_victories_when_board_is_valid() {
        Assert.assertThat(getMessage(), refereeService.isWinningDiagonalToNE(board, move, rowNumber), is(isVictory));
    }
}
