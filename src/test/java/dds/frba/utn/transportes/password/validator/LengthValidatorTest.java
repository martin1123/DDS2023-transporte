package dds.frba.utn.transportes.password.validator;

import dds.frba.utn.transportes.exception.PasswordRuleException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LengthValidatorTest {

    private static final int MIN_PASSWORD_LENGTH = 8;

    @Test
    public void passwordWithValidLengthTest(){
        String passwordWithValidLength = "abdc1234";
        LengthValidator lengthValidator = new LengthValidator(MIN_PASSWORD_LENGTH);
        assertDoesNotThrow(() -> lengthValidator.validatePassword(passwordWithValidLength));
    }

    @Test
    public void passwordWithInvalidLengthTest(){
        String passwordWithInvalidLength = "bdc1234";
        LengthValidator lengthValidator = new LengthValidator(MIN_PASSWORD_LENGTH);
        Exception exception = assertThrows(PasswordRuleException.class,
           () -> lengthValidator.validatePassword(passwordWithInvalidLength));
        assertEquals("The password does not have the required length.", exception.getMessage());
    }
}
