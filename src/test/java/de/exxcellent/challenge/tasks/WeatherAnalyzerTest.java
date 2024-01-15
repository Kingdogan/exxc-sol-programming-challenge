package de.exxcellent.challenge.tasks;

import de.exxcellent.challenge.utils.io.CsvReader;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class WeatherAnalyzerTest {

  @Test
  void testWeatherAnalyzer() {
    String fileName = "weather.csv";
    try {
      List<String[]> data = new CsvReader().readFile(fileName);
      String day = WeatherAnalyzer.findDayWithSmallestTemperatureSpread(data);
      assertEquals("14", day);
    } catch (IOException | URISyntaxException e) {
      fail(e.getMessage());
    }
  }

  @Test
  void testMissingColumnDay() {
    List<String[]> testData = new ArrayList<>();
    testData.add(new String[]{"MxT","MnT"});
    Exception exception = assertThrows(IllegalArgumentException.class, () -> WeatherAnalyzer.findDayWithSmallestTemperatureSpread(testData));
    assertEquals("Missing column: Day", exception.getMessage());
  }

  @Test
  void testMissingColumnMxT() {
    List<String[]> testData = new ArrayList<>();
    testData.add(new String[]{"Day","MnT"});
    Exception exception = assertThrows(IllegalArgumentException.class, () -> WeatherAnalyzer.findDayWithSmallestTemperatureSpread(testData));
    assertEquals("Missing column: MxT", exception.getMessage());
  }

  @Test
  void testMissingColumnMnT() {
    List<String[]> testData = new ArrayList<>();
    testData.add(new String[]{"Day", "MxT"});
    Exception exception = assertThrows(IllegalArgumentException.class, () -> WeatherAnalyzer.findDayWithSmallestTemperatureSpread(testData));
    assertEquals("Missing column: MnT", exception.getMessage());
  }
}