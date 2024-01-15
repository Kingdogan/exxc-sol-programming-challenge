package de.exxcellent.challenge.tasks;

import de.exxcellent.challenge.utils.io.CsvReader;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

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
}