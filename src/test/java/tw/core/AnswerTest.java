package tw.core;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import tw.core.exception.OutOfRangeAnswerException;
import tw.core.model.Record;

import java.util.Arrays;

import static junit.framework.Assert.assertFalse;
import static junit.framework.TestCase.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * 在AnswerTest文件中完成Answer中对应的单元测试
 */
public class AnswerTest {
    Answer answer;
    Answer answerError;

    @Before
    public void setUp() throws Exception {
        answer = Answer.createAnswer("1 2 3 4");
        answerError = Answer.createAnswer("1 1 2 3");
    }

    @Test
    public void testCreateAnswer() {
        assertNotNull(answer);
    }

    @Test
    public void testValidate() throws OutOfRangeAnswerException {
        answer.setNumList(Arrays.asList(new String[]{"2", "3", "4", "5"}));
        answer.validate();
        assertEquals(0, answer.getIndexOfNum("2"));
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testValidateThrowException() throws OutOfRangeAnswerException {
        thrown.expect(OutOfRangeAnswerException.class);
        thrown.expectMessage("Answer format is incorrect");
        answerError.validate();
    }

    @Test
    public void testCheck() {
        Answer inputAnswer = Answer.createAnswer("3 4 5 6");
        assertEquals("0A2B",answer.check(inputAnswer).getValue());

        inputAnswer.setNumList(Arrays.asList(new String[]{"1","2","5","6"}));
        assertEquals("2A0B",answer.check(inputAnswer).getValue());

        inputAnswer.setNumList(Arrays.asList(new String[]{"1","2","4","6"}));
        assertEquals("2A1B",answer.check(inputAnswer).getValue());
    }

}