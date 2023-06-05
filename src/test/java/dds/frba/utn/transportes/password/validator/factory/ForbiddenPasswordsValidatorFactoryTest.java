package dds.frba.utn.transportes.password.validator.factory;

import dds.frba.utn.transportes.exception.PasswordRuleFactoryException;
import dds.frba.utn.transportes.password.validator.ForbiddenPasswordsValidator;
import dds.frba.utn.transportes.password.validator.PasswordRule;
import dds.frba.utn.transportes.reader.FileReader;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ForbiddenPasswordsValidatorFactoryTest {

    @Test
    public void testCreateFactoryWithNullReaderThrowsException() {
        Exception ex = assertThrows(PasswordRuleFactoryException.class,
            () -> new ForbiddenPasswordsValidatorFactory("filePath", null));
        assertEquals("The file reader cannot be null.", ex.getMessage());
    }

    @Test
    public void testCreateFactoryWithNullFilePathThrowsException() {
        Exception ex = assertThrows(PasswordRuleFactoryException.class,
            () -> new ForbiddenPasswordsValidatorFactory(null, mock(FileReader.class)));
        assertEquals("The worst password file path cannot be null or empty.", ex.getMessage());
    }

    @Test
    public void testCreateFactoryWithEmptyFilePathThrowsException() {
        Exception ex = assertThrows(PasswordRuleFactoryException.class,
            () -> new ForbiddenPasswordsValidatorFactory("   \n\t  ", mock(FileReader.class)));
        assertEquals("The worst password file path cannot be null or empty.", ex.getMessage());
    }
    
    @Test
    public void testCreateForbiddenPasswordValidatorInstanceOk() {
        FileReader fileReader = mock(FileReader.class);
        String filePath = "file.txt";

        ForbiddenPasswordsValidatorFactory forbiddenPasswordsValidatorFactory =
            new ForbiddenPasswordsValidatorFactory(filePath, fileReader);

        List<String> forbiddenPasswords = Arrays.asList("abcd", "1234", "qwerty");
        when(fileReader.readFile(filePath)).thenReturn(forbiddenPasswords);

        PasswordRule passwordRule = forbiddenPasswordsValidatorFactory.createPasswordRule();
        assertNotNull(passwordRule);
        assertThat(passwordRule, instanceOf(ForbiddenPasswordsValidator.class));
    }

    @Test
    public void testCreateTwoForbiddenPasswordValidatorInstanceOk() {
        FileReader fileReader = mock(FileReader.class);
        String filePath = "file.txt";

        ForbiddenPasswordsValidatorFactory forbiddenPasswordsValidatorFactory =
            new ForbiddenPasswordsValidatorFactory(filePath, fileReader);

        List<String> forbiddenPasswords = Arrays.asList("abcd", "1234", "qwerty");
        when(fileReader.readFile(filePath)).thenReturn(forbiddenPasswords);

        PasswordRule passwordRule = forbiddenPasswordsValidatorFactory.createPasswordRule();
        PasswordRule passwordRule2 = forbiddenPasswordsValidatorFactory.createPasswordRule();

        //Verifies that the factory reads the file one time.
        verify(fileReader, times(1)).readFile(filePath);
        //The second passwordRule must be the first instance created,
        assertThat(passwordRule, is(passwordRule2));
    }
}
