package dds.frba.utn.transportes.password.validator;

import dds.frba.utn.transportes.exception.PasswordRuleException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LengthValidator implements PasswordRule {

	private static Logger logger = LoggerFactory.getLogger(LengthValidator.class);

	private int passwordMinLength;

	public LengthValidator (final int thePasswordMinLength) {
		passwordMinLength = thePasswordMinLength;
	}
	
	@Override
	public void validatePassword(final String password) throws PasswordRuleException {
		if (password.length() < passwordMinLength) {
			String errorMsg = "The password does not have the required length.";
			logger.debug(errorMsg);
			throw new PasswordRuleException(errorMsg);
		}
	}
}
