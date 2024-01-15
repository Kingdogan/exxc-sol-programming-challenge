package de.exxcellent.challenge.tasks;

import java.util.List;

public class WeatherAnalyzer{

  private static final float INITIAL_MIN_TEMP_SPREAD = 1000000000;

  /**
   * This method finds the day with the smallest temperature spread in the given data
   * @param weatherData Data of the weather
   * @return Day with the smallest temperature spread
   */
  public static String findSmallestSpread(List<String[]> weatherData) {
    // First we check if the columns Day, MxT and MnT exist
    int columnDay = findColumnIndex(weatherData, "Day");
    if (columnDay == -1) {
      throw new IllegalArgumentException("Missing column: Day");
    }
    int columnMxT = findColumnIndex(weatherData, "MxT");
    if (columnMxT == -1) {
      throw new IllegalArgumentException("Missing column: MxT");
    }
    int columnMnT = findColumnIndex(weatherData, "MnT");
    if (columnMnT == -1) {
      throw new IllegalArgumentException("Missing column: MnT");
    }

    float minTempSpread= INITIAL_MIN_TEMP_SPREAD;
    String daySmallestSpread = "";
    for (int i = 1; i < weatherData.size(); i++) {
      float maxTemp = Float.parseFloat(weatherData.get(i)[columnMxT]);
      float minTemp = Float.parseFloat(weatherData.get(i)[columnMnT]);
      float tempSpread = maxTemp - minTemp;
      if (tempSpread < minTempSpread) {
        daySmallestSpread = weatherData.get(i)[columnDay];
        minTempSpread = tempSpread;
      }
    }
    return daySmallestSpread;
  }

  private static int findColumnIndex(List<String[]> data, String target) {
    for (String[] line : data) {
      for (int i = 0; i < line.length; i++) {
        if (line[i].equals(target)) {
          return i;
        }
      }
    }
    return -1;
  }
}
