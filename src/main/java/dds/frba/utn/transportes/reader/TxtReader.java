package dds.frba.utn.transportes.reader;

import com.google.common.io.Resources;
import dds.frba.utn.transportes.exception.FileReaderException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import static com.google.common.io.Resources.getResource;

public class TxtReader implements FileReader {

	/**
	 * The class logger.
	 */
	private static Logger logger = LoggerFactory.getLogger(TxtReader.class);

	@Override
	public List<String> readFile(String filePath) {
		String fileText = "";
		
		try {
			URL fileURL = getResource(filePath);
			fileText = Resources.toString(fileURL, StandardCharsets.UTF_8);
			fileText = fileText.replace("\n", ",");
			fileText = fileText.replace("\r", "");
		} catch(IllegalArgumentException ex) {
			throwErrorMessage("The file does not exist. Description: ", ex.getMessage());
		} catch (IOException ex) {
			throwErrorMessage("There is a problem reading the file. Description: ", ex.getMessage());
		} catch (Exception ex) {
			throwErrorMessage("The following Error has ocurred: ", ex.getMessage());
		}

		return Arrays.asList(fileText.split(","));
	}

	private void throwErrorMessage(String customMessage, String exceptionMessage) {
		String errorMsg = customMessage + exceptionMessage;
		logger.error(errorMsg);
		throw new FileReaderException(errorMsg);
	}

}
