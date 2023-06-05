package dds.frba.utn.transportes.password.validator;

import dds.frba.utn.transportes.exception.PasswordRuleException;

import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SpecialCharacterValidator implements PasswordRule {

   /**
	* The class logger.
	*/
	private static Logger logger = LoggerFactory.getLogger(SpecialCharacterValidator.class);
   
    @Override
	public void validatePassword(String password) throws PasswordRuleException {
        Pattern special = Pattern.compile ("[!@#$%&*()_+=|<>?{}\\[\\]~-]");
        boolean hasSpecial = special.matcher(password).find();
       if(!hasSpecial) {
           String errorMsg = "The Password does not have any special character.";
           logger.debug(errorMsg);
    	   throw new PasswordRuleException(errorMsg);
       }
	}

}
