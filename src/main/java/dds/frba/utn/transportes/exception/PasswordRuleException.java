package dds.frba.utn.transportes.exception;

public class PasswordRuleException extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Class constructor.
	 *
	 * @param errorMessage the error message.
	 */
	public PasswordRuleException(String errorMessage) {
		super(errorMessage);
	}
}
