package dds.frba.utn.transportes.password.validator;

import dds.frba.utn.transportes.exception.PasswordRuleException;

import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DigitValidator implements PasswordRule {

    private static Logger logger = LoggerFactory.getLogger(DigitValidator.class);

    @Override
    public void validatePassword(String password) throws PasswordRuleException {
        Pattern digit = Pattern.compile("[0-9]");
        boolean hasDigit = digit.matcher(password).find();
        if(!hasDigit) {
            String errorMsg = "The password does not have any digit.";
            logger.error(errorMsg);
    	    throw new PasswordRuleException(errorMsg);
        }
    }
    
}
