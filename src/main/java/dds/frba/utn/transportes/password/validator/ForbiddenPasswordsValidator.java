package dds.frba.utn.transportes.password.validator;

import dds.frba.utn.transportes.exception.PasswordRuleException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ForbiddenPasswordsValidator implements PasswordRule {

	private static Logger logger = LoggerFactory.getLogger(ForbiddenPasswordsValidator.class);

	private List<String> worstPasswords;

	public ForbiddenPasswordsValidator(final List<String> theWorstPasswords) {
		if(theWorstPasswords == null) {
			String errorMsg = "The worst passwords list cannot be null.";
			logger.error(errorMsg);
			throw new PasswordRuleException(errorMsg);
		}
		worstPasswords = theWorstPasswords;
	}
	
	@Override
    public void validatePassword(final String password) throws PasswordRuleException {
		if(worstPasswords.stream().anyMatch(wp -> wp.equals(password))) {
			String errorMsg = "The password is not strong combination.";
			logger.debug(errorMsg);
			throw new PasswordRuleException(errorMsg);
		}
	}
}
