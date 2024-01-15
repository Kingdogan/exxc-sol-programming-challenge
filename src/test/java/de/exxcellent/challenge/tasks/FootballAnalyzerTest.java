package de.exxcellent.challenge.tasks;

import de.exxcellent.challenge.utils.io.CsvReader;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FootballAnalyzerTest {

  @Test
  void testFootballAnalyzer() {
    String fileName = "football.csv";
    try {
      List<String[]> data = new CsvReader().readFile(fileName);
      String team = FootballAnalyzer.findSmallestSpread(data);
      assertEquals("Aston_Villa", team);
    } catch (IOException | URISyntaxException e) {
      fail(e.getMessage());
    }
  }

  @Test
  void testMissingColumnTeam() {
    List<String[]> testData = new ArrayList<>();
    testData.add(new String[]{"Goals","Goals Allowed"});
    Exception exception = assertThrows(IllegalArgumentException.class, () -> FootballAnalyzer.findSmallestSpread(testData));
    assertEquals("Missing column: Team", exception.getMessage());
  }

  @Test
  void testMissingColumnGoals() {
    List<String[]> testData = new ArrayList<>();
    testData.add(new String[]{"Team","Goals Allowed"});
    Exception exception = assertThrows(IllegalArgumentException.class, () -> FootballAnalyzer.findSmallestSpread(testData));
    assertEquals("Missing column: Goals", exception.getMessage());
  }

  @Test
  void testMissingColumnGoalsAllowed() {
    List<String[]> testData = new ArrayList<>();
    testData.add(new String[]{"Team", "Goals"});
    Exception exception = assertThrows(IllegalArgumentException.class, () -> FootballAnalyzer.findSmallestSpread(testData));
    assertEquals("Missing column: Goals Allowed", exception.getMessage());
  }
}