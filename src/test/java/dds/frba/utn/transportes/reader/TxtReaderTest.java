package dds.frba.utn.transportes.reader;

import dds.frba.utn.transportes.exception.FileReaderException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TxtReaderTest {

	private TxtReader fileReader = new TxtReader();
	private String filePath;
	
	@BeforeEach
	public void setup() {
		filePath = "Txt Reader Test.txt";
	}
	
	@Test
	public void txtContainsExpectedWord() {
		List<String> words = new ArrayList<>();
		String word = "Transporte";
		
		words = fileReader.readFile(filePath);
		assertTrue(words.contains(word));
	}
	
	@Test 
	public void txtDoesNotContainExpectedWord() {
		List<String> words = new ArrayList<>();
		String word = "perro";
		
		words = fileReader.readFile(filePath);
		assertFalse(words.contains(word));
	}
	
	@Test
	public void fileDoesNotExistTest() {
		String filePathDoesNotExist = "TextDoesNotExist.txt";
		
		Exception exception = assertThrows(FileReaderException.class,
				() -> fileReader.readFile(filePathDoesNotExist));
		assertEquals("The file does not exist. Description: resource TextDoesNotExist.txt not found.", exception.getMessage());
	}
}
