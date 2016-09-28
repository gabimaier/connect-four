package gm.connectfour.domain.repository;

import gm.connectfour.domain.entity.Board;
import gm.connectfour.domain.entity.Game;
import gm.connectfour.domain.entity.GameState;
import gm.connectfour.domain.entity.Player;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import static gm.connectfour.domain.entity.Player.*;
import static java.lang.Boolean.FALSE;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class GameRepositoryIntegrationTest {
    @Inject
    private GameRepository gameRepository;

    @Test
    @Transactional  //TODO:read
    public void should_support_crud_operations_when_valid_game_passed() {
        //given

        final Player[][] initialBoard = {
                {NONE, NONE, NONE},
                {NONE, NONE, NONE},
                {NONE, NONE, NONE},
                {NONE, NONE, NONE}
        };
        final Player[][] expectedBoard = {
                {NONE, O, X},
                {X, X, O},
                {O, X, O},
                {O, O, X}
        };

        final String id = testCreate(initialBoard);
        testUpdate(id, expectedBoard);
        testRead(id, expectedBoard);
        testDelete(id);
    }

    @Transactional
    private String testCreate(Player[][] expectedBoard) {
        final Game newGame= new Game();
        newGame.setDeleted(true);
        newGame.setLastColour(Player.O);
        newGame.setState(GameState.NOT_FINISHED);
        newGame.setBoard(Board.builder().build(expectedBoard));

        final Game savedGame = gameRepository.save(newGame);

        assertThat(savedGame, notNullValue());
        assertThat(savedGame.getId(), notNullValue());
        return savedGame.getId();
    }

    @Transactional
    private void testDelete(String id) {
        gameRepository.delete(id);
        final boolean wasDeleted = gameRepository.exists(id);
        assertThat(wasDeleted, is(FALSE));
    }

    @Transactional(readOnly = true)
    private void testUpdate(String id, Player[][] expectedBoard) {
        final Game game = gameRepository.findOne(id);
        game.setBoard(Board.builder().build(expectedBoard));
        gameRepository.save(game);
    }

    @Transactional
    private void testRead(String id, Player[][] expectedBoard) {
        final Game game = gameRepository.findOne(id);
        assertThat(game.getBoard(), is(Board.builder().build(expectedBoard)));
    }
}