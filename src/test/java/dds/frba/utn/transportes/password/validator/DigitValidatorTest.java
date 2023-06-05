package dds.frba.utn.transportes.password.validator;

import dds.frba.utn.transportes.exception.PasswordRuleException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DigitValidatorTest {
    

    @Test
    public void passwordWithoutDigitsTest(){
        String passwordWithoutDigits = "abdc";
        PasswordRule digitValidator = new DigitValidator();
        Exception exception = assertThrows(PasswordRuleException.class,
           () -> digitValidator.validatePassword(passwordWithoutDigits));
        assertEquals("The password does not have any digit.", exception.getMessage());
    }

    @Test
    public void passwordWithDigitsTest(){
        String passwordWithDigits = "123";
        PasswordRule digitValidator = new DigitValidator();
        assertDoesNotThrow(() -> digitValidator.validatePassword(passwordWithDigits));
    }
}
