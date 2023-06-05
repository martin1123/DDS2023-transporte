package dds.frba.utn.transportes.password.validator;

import dds.frba.utn.transportes.exception.PasswordRuleException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SpecialCharacterValidatorTest {

    @Test
    public void passwordWithoutAnySpecialCharacterTest(){
        String passwordWithoutSpecialChar = "12fasdfs345";
        PasswordRule specialCharacterValidator = new SpecialCharacterValidator();
        Exception exception = assertThrows(PasswordRuleException.class,
           () -> specialCharacterValidator.validatePassword(passwordWithoutSpecialChar));
        assertEquals("The Password does not have any special character.", exception.getMessage());
    }

    @Test
    public void passwordWithSpecialCharacterTest(){
        String passwordWithSpecialChar = "123a@bc45";
        PasswordRule specialCharacterValidator = new SpecialCharacterValidator();
        assertDoesNotThrow(() -> specialCharacterValidator.validatePassword(passwordWithSpecialChar));
    }
}
