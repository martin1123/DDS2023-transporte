package dds.frba.utn.transportes.password.validator;

import dds.frba.utn.transportes.exception.PasswordRuleException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LetterValidatorTest {

    @Test
    public void passwordWithoutLettersTest(){
        String passwordWithoutLetters = "12345";
        PasswordRule letterValidator = new LetterValidator();
        Exception exception = assertThrows(PasswordRuleException.class,
           () -> letterValidator.validatePassword(passwordWithoutLetters));
        assertEquals("The password does not have any letter.", exception.getMessage());
    }

    @Test
    public void passwordWithLettersTest(){
        String passwordWithLetters = "123abc45";
        PasswordRule letterValidator = new LetterValidator();
        assertDoesNotThrow(() -> letterValidator.validatePassword(passwordWithLetters));
    }
}
