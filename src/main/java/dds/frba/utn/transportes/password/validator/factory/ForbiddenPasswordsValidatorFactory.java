package dds.frba.utn.transportes.password.validator.factory;

import dds.frba.utn.transportes.exception.PasswordRuleFactoryException;
import dds.frba.utn.transportes.password.validator.ForbiddenPasswordsValidator;
import dds.frba.utn.transportes.password.validator.PasswordRule;

import java.util.List;

import dds.frba.utn.transportes.reader.FileReader;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ForbiddenPasswordsValidatorFactory implements PasswordRuleFactory {

    private static Logger logger = LoggerFactory.getLogger(ForbiddenPasswordsValidatorFactory.class);

    private String worstPasswordsFilePath;

    private FileReader fileReader;

    private PasswordRule instanceOfForbiddenPasswordsValidator;

    public ForbiddenPasswordsValidatorFactory(final String theWorstPasswordsFilePath, final FileReader theFileReader) {
        if(StringUtils.isBlank(theWorstPasswordsFilePath)) {
            throwConstructorException("The worst password file path cannot be null or empty.");
        }

        if(theFileReader == null) {
            throwConstructorException("The file reader cannot be null.");
        }
        this.worstPasswordsFilePath = theWorstPasswordsFilePath;
        this.fileReader = theFileReader;
    }

    private void throwConstructorException(final String errorMsg) {
        logger.error(errorMsg);
        throw new PasswordRuleFactoryException(errorMsg);
    }

    @Override
    public PasswordRule createPasswordRule() {
        if(instanceOfForbiddenPasswordsValidator != null) {
            return instanceOfForbiddenPasswordsValidator;
        }

        logger.debug(String.format("Reading file: %s", worstPasswordsFilePath));
        List<String> worstPasswordsList = fileReader.readFile(worstPasswordsFilePath);
        PasswordRule forbiddenPasswordsValidator =  new ForbiddenPasswordsValidator(worstPasswordsList);
        this.instanceOfForbiddenPasswordsValidator = forbiddenPasswordsValidator;
        logger.debug("PasswordRule instance was created and saved.");
        return forbiddenPasswordsValidator;
    }
}
