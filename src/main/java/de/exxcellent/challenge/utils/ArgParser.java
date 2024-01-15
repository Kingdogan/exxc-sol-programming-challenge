package de.exxcellent.challenge.utils;

import org.apache.commons.cli.*;

/**
 * This class is used to parse the CLI arguments
 *
 * @author Öner Aydogan
 */
public class ArgParser {

  /**
   * This function parses the command line arguments
   * @param args The CLI arguments
   * @return The parsed CLI arguments
   * @throws ParseException If there is an error parsing the arguments.
   */
  public static CommandLine parseArgs(String... args) throws ParseException {
    CommandLineParser parser = new DefaultParser();
    Options options = createOptions();

    CommandLine cmd = null;
    try {
      cmd = parser.parse(options, args);
    } catch (ParseException e) {
      String errMessage = "Error parsing command-line arguments: " + e.getMessage();
      throw new ParseException(errMessage);
    }
    return cmd;
  }

  /**
   * Create the options for this coding challenge
   */
  private static Options createOptions() {
    Options options = new Options();

    Option weatherTask = new Option(
        "w",
        "weather",
        true,
        "Executes the weather task which reads the given input file and outputs the day with the " +
            "smallest temperature spread.");
    weatherTask.setArgName("inputFile");
    options.addOption(weatherTask);

    Option footballTask = new Option(
        "f",
        "football",
        true,
        "Executes the football task which reads the given input file and outputs the team with the " +
            "smallest distance (absolute difference) between goals and goals allowed.");
    footballTask.setArgName("inputFile");
    options.addOption(footballTask);

    return options;
  }
}
