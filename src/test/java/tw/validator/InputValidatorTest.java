package tw.validator;

import org.junit.Test;


import static org.junit.Assert.*;

public class InputValidatorTest {

    @Test
    public void testValidate() {
        InputValidator inputValidator = new InputValidator();
        assertTrue(inputValidator.validate("1 2 3 4"));
        assertFalse(inputValidator.validate("1 2 3"));
        assertFalse(inputValidator.validate("1 1 2 3"));
    }
}