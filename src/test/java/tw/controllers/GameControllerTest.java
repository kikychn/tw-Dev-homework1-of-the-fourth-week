package tw.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import tw.commands.InputCommand;
import tw.core.Answer;
import tw.core.Game;
import tw.core.model.GuessResult;
import tw.views.GameView;

import java.io.IOException;

import static org.mockito.Mockito.*;

/**
 * 在GameControllerTest文件中完成GameController中对应的单元测试
 */

public class GameControllerTest {

    Game game;
    GameView gameView;
    InputCommand inputCommand;
    GuessResult guessResult;
    GameController gameController;

    @Before
    public void setUp() throws Exception {
        game = mock(Game.class);
        gameView = mock(GameView.class);
        inputCommand = mock(InputCommand.class);
        guessResult = mock(GuessResult.class);
        gameController = new GameController(game, gameView);
        gameController = spy(gameController);
    }

    @Test
    public void testPlay_gameStatus_0_Continue_0_Others() throws IOException {
        when(game.checkCoutinue()).thenReturn(false);
        gameController.play(inputCommand);

        verify(gameController, times(1)).play(inputCommand);
        verify(gameView, times(1)).showGameStatus(game.checkStatus());
    }

    @Test
    public void testPlay_gameStatus_1_Continue_1_Others() throws IOException {
        when(game.checkCoutinue()).thenReturn(true, false);
        when(game.guess(inputCommand.input())).thenReturn(guessResult);
        gameController.play(inputCommand);

        verify(gameController, times(2)).play(inputCommand);
        verify(game, times(1)).guess(inputCommand.input());
        verify(gameView, times(1)).showGuessResult(guessResult);
        verify(gameView, times(1)).showGuessHistory(game.guessHistory());
        verify(gameView, times(1)).showGameStatus(game.checkStatus());
    }

    @Test
    public void testPlay_gameStatus_6_Continue_1_Others() throws IOException {
        when(game.checkCoutinue()).thenReturn(true, true, true, true, true, true, false);
        when(game.guess(inputCommand.input())).thenReturn(guessResult);
        gameController.play(inputCommand);

        verify(gameController, times(7)).play(inputCommand);
        verify(game, times(6)).guess(inputCommand.input());
        verify(gameView, times(6)).showGuessResult(guessResult);
        verify(gameView, times(6)).showGuessHistory(game.guessHistory());
        verify(gameView, times(1)).showGameStatus(game.checkStatus());
    }
}