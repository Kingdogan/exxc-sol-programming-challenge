package de.exxcellent.challenge.utils.io;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.BaseStream;

import static org.junit.jupiter.api.Assertions.*;

public class CsvReaderTest {

  @Test
  void testCsvReader() {
    String fileName = "weather.csv";

    try {
      List<String[]> data = new CsvReader().readFile(fileName);
      assertNotNull(data);
      assertEquals(31, data.size());
      // This is line 21 from the weather.csv file
      String[] expectedLine = new String[] {"20", "84", "57", "71", "58.9", "0", "150", "6.3", "160", "13", "3.6", "90", "43", "1032.5"};
      String[] testedLine = data.get(20);
      for (int i = 0; i < expectedLine.length; i++) {
        assertEquals(expectedLine[i], testedLine[i]);
      }
    } catch (IOException | URISyntaxException e) {
      fail(e.getMessage());
    }
  }

  @Test
  void testWrongFileType() {
    String fileName = "test.txt";
    Exception exception = assertThrows(IOException.class, () -> new CsvReader().readFile(fileName));
    assertEquals("The provided file is not a CSV file.", exception.getMessage());
  }
}
