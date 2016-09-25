package gm.connectfour.service;

import gm.connectfour.domain.entity.Player;
import gm.connectfour.dto.Move;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.Matchers.is;

public class RefereeServiceColumnWinTest extends AbstractRefereeServiceMoveTest {
    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {true,  1, Move.builder().column(3).player(Player.X).build(),
                    //   012345
                        "......\n" +    //0
                        "......\n" +    //1
                        "...x..\n" +    //2
                        "...x..\n" +    //3
                        "...x..\n"      //4
                },
                {true,  0, Move.builder().column(3).player(Player.X).build(),
                    //   012345
                        "......\n" +    //0
                        "...x..\n" +    //1
                        "...x..\n" +    //2
                        "...x..\n"     //3
                },
                {false,  1, Move.builder().column(3).player(Player.X).build(),
                    //   012345
                        "......\n" +    //0
                        "......\n" +    //1
                        "...x..\n" +    //2
                        "...x..\n" +    //3
                        "...o..\n"      //4
                },
                {true,  1, Move.builder().column(3).player(Player.X).build(),
                    //   012345
                        "......\n" +    //0
                        "......\n" +    //1
                        "...x..\n" +    //2
                        "...x..\n" +    //3
                        "...x..\n" +    //4
                        "...o..\n"      //5
                },
                {false, 1, Move.builder().column(3).player(Player.X).build(),
                    //   012345
                        "......\n" +    //0
                        "......\n" +    //1
                        "...x..\n" +    //2
                        "...x..\n" +    //3
                        "...o..\n" +    //4
                        "...x..\n"      //5
                }
        });
    }

    public RefereeServiceColumnWinTest(boolean expected, int rowNumber, Move move, String strBoard) {
        super(expected, rowNumber, move, strBoard);
    }

    @Test
    public void should_recognize_victories_when_board_is_valid() {
        Assert.assertThat(getMessage(), refereeService.isWinningColumn(board, move, rowNumber), is(isVictory));
    }
}
