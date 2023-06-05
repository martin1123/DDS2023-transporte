package dds.frba.utn.transportes.password.validator;

import dds.frba.utn.transportes.exception.PasswordRuleException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ForbiddenPasswordsValidatorTest {
    
    @Test
    public void createForbiddenPasswordsValidatorWithNullList () {
        Exception ex = assertThrows(PasswordRuleException.class,
            () -> new ForbiddenPasswordsValidator(null));
        assertEquals("The worst passwords list cannot be null.", ex.getMessage());
    }

    @Test
    public void testAPasswordOfForbiddenListDoesNotPassValidation () {
        List<String> forbiddenPasswords = new ArrayList<>(Arrays.asList("abdc", "abcd1234", "test"));
        ForbiddenPasswordsValidator passwordValidator = new ForbiddenPasswordsValidator(forbiddenPasswords);

        Exception ex = assertThrows(PasswordRuleException.class,
            () -> passwordValidator.validatePassword("abcd1234"));
        assertEquals("The password is not strong combination.", ex.getMessage());
    }

    @Test
    public void testAPasswordThatNotAppearsInForbiddenListPassValidation () {
        List<String> forbiddenPasswords = new ArrayList<>(Arrays.asList("abdc"));
        ForbiddenPasswordsValidator passwordValidator = new ForbiddenPasswordsValidator(forbiddenPasswords);

        assertDoesNotThrow(() -> passwordValidator.validatePassword("test"));
    }
}
