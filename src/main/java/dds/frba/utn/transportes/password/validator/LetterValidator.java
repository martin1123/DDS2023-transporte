package dds.frba.utn.transportes.password.validator;

import dds.frba.utn.transportes.exception.PasswordRuleException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Pattern;


public class LetterValidator implements PasswordRule {

	private static Logger logger = LoggerFactory.getLogger(LetterValidator.class);
    
    @Override
    public void validatePassword(String password) throws PasswordRuleException {
        Pattern letter = Pattern.compile("[a-zA-z]");
        boolean hasLetter = letter.matcher(password).find();

        if(!hasLetter) {
            String errorMsg = "The password does not have any letter.";
            logger.debug(errorMsg);
            throw new PasswordRuleException(errorMsg);
        }
    }

}
