package de.exxcellent.challenge.utils;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.ParseException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class to test the functionality of the arg parser
 */
public class ArgParserTest {
  @Test
  void testParseArgsWeatherTask() {
    String[] args = {"--weather", "weather.csv"};
    CommandLine cmd = null;
    try {
      cmd = ArgParser.parseArgs(args);
    } catch (ParseException e) {
      fail(e.getMessage());
    }
    assertTrue(cmd.hasOption("weather"));
    assertEquals("weather.csv", cmd.getOptionValue("w"));
  }

  @Test
  void testParseArgsFootballTask() {
    String[] args = {"--football", "football.csv"};
    CommandLine cmd = null;
    try {
      cmd = ArgParser.parseArgs(args);
    } catch (ParseException e) {
      fail(e.getMessage());
    }
    assertTrue(cmd.hasOption("football"));
    assertEquals("football.csv", cmd.getOptionValue("f"));
  }

  @Test
  void testParseArgsEmptyArgs() {
    String[] args = {};
    CommandLine cmd = null;
    try {
      cmd = ArgParser.parseArgs(args);
    } catch (ParseException e) {
      fail(e.getMessage());
    }
    assertEquals(0, cmd.getOptions().length);
  }

  @Test
  void testParseArgsInvalidOption() {
    String[] args = {"--invalidOption", "test.csv"};
    Exception exception = assertThrows(ParseException.class, () -> ArgParser.parseArgs(args));
    String expectedErrorMsg = "Error parsing command-line arguments: Unrecognized option: --invalidOption";
    assertEquals(expectedErrorMsg, exception.getMessage());
  }

  @Test
  void testParseArgsNoFileGiven() {
    String[] args = {"--weather"};
    Exception exception = assertThrows(ParseException.class, () -> ArgParser.parseArgs(args));
    String expectedErrorMsg = "Error parsing command-line arguments: Missing argument for option: w";
    assertEquals(expectedErrorMsg, exception.getMessage());
  }
}
