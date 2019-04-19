package tw.core.generator;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;

import static org.junit.Assert.*;

public class RandomIntGeneratorTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testGenerateNums() {
        RandomIntGenerator randomIntGenerator = new RandomIntGenerator();
        thrown.expect(IllegalArgumentException.class);
        randomIntGenerator.generateNums(3, 4);

        String result = randomIntGenerator.generateNums(6, 5);
        assertEquals(5, Arrays.asList(result.split(" ")).stream().filter(i -> Integer.valueOf(i) <= 6).distinct().count());
    }
}