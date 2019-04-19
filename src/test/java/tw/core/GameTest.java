package tw.core;

import org.junit.Before;
import org.junit.Test;
import tw.core.generator.AnswerGenerator;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * 在GameTest文件中完成Game中对应的单元测试
 */


public class GameTest {

    Game game;

    @Before
    public void setUp() throws Exception {
        AnswerGenerator answerGenerator = mock(AnswerGenerator.class);
        when(answerGenerator.generate()).thenReturn(Answer.createAnswer("1 2 3 4"));
        game = new Game(answerGenerator);
    }

    @Test
    public void testGuess() {
        assertEquals("2A0B", game.guess(Answer.createAnswer("1 2 5 6")).getResult());
        assertEquals("0A2B", game.guess(Answer.createAnswer("2 1 5 6")).getResult());
        assertEquals("0A0B", game.guess(Answer.createAnswer("7 8 5 6")).getResult());
        assertEquals("4A0B", game.guess(Answer.createAnswer("1 2 3 4")).getResult());
    }

    @Test
    public void testGuessHistory() {
        game.guess(Answer.createAnswer("1 2 5 6")).getResult();
        game.guess(Answer.createAnswer("2 1 5 6")).getResult();
        assertEquals(2, game.guessHistory().size());
        assertEquals("2A0B", game.guessHistory().get(0).getResult());
        assertEquals("0A2B", game.guessHistory().get(1).getResult());
    }

}
