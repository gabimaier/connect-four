package gm.connectfour.service;

import gm.connectfour.domain.entity.Player;
import gm.connectfour.dto.Move;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.Matchers.is;

public class RefereeServiceLineWinTest extends AbstractRefereeServiceMoveTest {
    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {true, 1, Move.builder().column(3).player(Player.X).build(),
                    //   012345
                        "......\n" +    //0
                        "xxx.oo\n"     //1
                },
                {true, 1, Move.builder().column(3).player(Player.X).build(),
                    //   012345
                        "......\n" +    //0
                        "oxx.xo\n"     //1
                },
                {true, 1, Move.builder().column(3).player(Player.X).build(),
                    //   012345
                        "......\n" +    //0
                        "oox.xx\n"     //1
                },
                {true, 1, Move.builder().column(3).player(Player.X).build(),
                    //   0123456
                        ".......\n" +    //0
                        "ooo.xxx\n"     //1
                },
                {true, 1, Move.builder().column(0).player(Player.X).build(),
                    //   012345
                        "......\n" +    //0
                        ".xxxoo\n"     //1
                },
                {true, 1, Move.builder().column(5).player(Player.X).build(),
                    //   012345
                        "......\n" +    //0
                        "ooxxx.\n"     //1
                },
                {false, 1, Move.builder().column(3).player(Player.X).build(),
                    //   012345
                        "......\n" +    //0
                        "oox.xo\n"     //1
                },
                {false, 1, Move.builder().column(3).player(Player.X).build(),
                    //   012345
                        "......\n" +    //0
                        "xxo.ox\n"     //1
                }
        });
    }

    public RefereeServiceLineWinTest(boolean expected, int rowNumber, Move move, String strBoard) {
        super(expected, rowNumber, move, strBoard);
    }

    @Test
    public void should_recognize_victories_when_board_is_valid() {
        Assert.assertThat(getMessage(), refereeService.isWinningLine(board, move, rowNumber), is(isVictory));
    }
}
