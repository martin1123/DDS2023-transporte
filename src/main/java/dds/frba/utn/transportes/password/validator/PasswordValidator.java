package dds.frba.utn.transportes.password.validator;

import dds.frba.utn.transportes.exception.PasswordRuleException;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PasswordValidator {

	private static Logger logger = LoggerFactory.getLogger(PasswordValidator.class);

	/**
	 * The unique instance of this class.
	 */
	private static PasswordValidator instance = new PasswordValidator();

	private List<PasswordRule> rules = new ArrayList<>();
	
	/**
	 * Private constructor to avoid multiple instances.
	 */
	private PasswordValidator() {}
	
	/**
	 * @return the class instance.
	 */
	public static PasswordValidator getPasswordValidator() {
		return instance ;
	}
	
	public void addRules(PasswordRule rule) {
		this.rules.add(rule);
	}

	public void validatePassword(String password) throws PasswordRuleException {
		if(StringUtils.isBlank(password)) {
			String errorMsg = "The password cannot be null or empty.";
			logger.debug(errorMsg);
			throw new PasswordRuleException(errorMsg);
		}

		this.rules.forEach(r -> r.validatePassword(password));
	}
}
