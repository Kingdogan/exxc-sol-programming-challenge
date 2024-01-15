package de.exxcellent.challenge.utils.io;

import de.exxcellent.challenge.App;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * CsvReader class for reading CSV files.
 */
public class CsvReader implements FileReader {
  /**
   * Reads the content of a CSV file.
   *
   * @param fileName The name of the CSV file.
   * @return List of string arrays representing the CSV data.
   * @throws IOException        If an I/O error occurs.
   * @throws URISyntaxException If the file URL is not a valid URI.
   */
  @Override
  public List<String[]> readFile(String fileName) throws IOException, URISyntaxException {
    // Check if file is csv file
    if (!fileName.endsWith(".csv")) {
      throw new IOException("The provided file is not a CSV file.");
    }
    URL fileURL = App.class.getClassLoader().getResource("de/exxcellent/challenge/" + fileName);
    List<String> lines = Files.readAllLines(Paths.get(fileURL.toURI()));
    List<String[]> data = new ArrayList<>();
    for (String line : lines) {
      String[] columns = line.split(",");
      data.add(columns);
    }
    return data;
  }
}
