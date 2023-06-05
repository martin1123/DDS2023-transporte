package dds.frba.utn.transportes.password.validator;

import dds.frba.utn.transportes.exception.PasswordRuleException;

public interface PasswordRule {

	void validatePassword(String password) throws PasswordRuleException;
}
